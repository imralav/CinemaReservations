package pl.com.imralav.vxml.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.com.imralav.vxml.filters.LogRequestFilter;

@Configuration
public class FiltersConfiguration {

    @Autowired
    private LogRequestFilter logRequestFilter;

    @Bean
    public FilterRegistrationBean registerLogRequestFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(logRequestFilter);
        registrationBean.addUrlPatterns(logRequestFilter.getPaths());
        registrationBean.setName("Request Logging Filter");
        return registrationBean;
    }
}
