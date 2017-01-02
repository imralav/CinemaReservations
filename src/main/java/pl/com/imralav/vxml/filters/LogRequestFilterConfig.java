package pl.com.imralav.vxml.filters;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="logrequestfilter")
public class LogRequestFilterConfig {
    private String paths;

    public String getPaths() {
        return paths;
    }


    public void setPaths(String paths) {
        this.paths = paths;
    }
}
