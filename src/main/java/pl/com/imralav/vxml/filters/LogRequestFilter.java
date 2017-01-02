package pl.com.imralav.vxml.filters;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

@Component
public class LogRequestFilter extends AbstractRequestLoggingFilter {

    private static final String NAME_VALUE_FORMAT = "{}={}";

    private static final String ALL_URLS = "/**";

    @Autowired
    private LogRequestFilterConfig config;

    private static final Logger LOGGER = LoggerFactory.getLogger(LogRequestFilter.class);

    public String[] getPaths() {
        String paths = config.getPaths();
        if (paths.isEmpty()) {
            return new String[] { ALL_URLS };
        }
        return paths.split(",");
    }

    @Override
    protected void initFilterBean() throws ServletException {
        this.setIncludePayload(true);
        this.setIncludeClientInfo(true);
        this.setIncludeHeaders(true);
        this.setIncludeQueryString(true);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        LOGGER.info("New {} request from: {}", request.getMethod(), request.getRequestURI());
        LOGGER.info("Standard message: {}", message);
        logHeaders(request);
        logParameters(request);
        logPayload(request);
        logAttributes(request);
    }

    private void logHeaders(HttpServletRequest request) {
        LOGGER.info("Headers:");
        forEachRemaining(request.getHeaderNames(), name -> {
            LOGGER.info(NAME_VALUE_FORMAT, name, request.getHeader(name));
        });
    }

    private void logAttributes(HttpServletRequest request) {
        LOGGER.info("Attributes:");
        forEachRemaining(request.getAttributeNames(), name -> {
            LOGGER.info(NAME_VALUE_FORMAT, name, request.getAttribute(name));
        });
    }

    private void logParameters(HttpServletRequest request) {
        LOGGER.info("Parameters:");
        request.getParameterMap().forEach((name, value) -> {
            LOGGER.info(NAME_VALUE_FORMAT, name, value);
        });
    }

    private void logPayload(HttpServletRequest request){
        LOGGER.info("Payload:");
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, getMaxPayloadLength());
                String payload;
                try {
                    payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    payload = "[unknown]";
                }
                LOGGER.info("{}", payload);
            }
        }
    }

    private <T> void forEachRemaining(Enumeration<T> e, Consumer<? super T> c) {
        while(e.hasMoreElements()) {
            c.accept(e.nextElement());
        }
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        LOGGER.info(message);
        logPayload(request);
    }

}
