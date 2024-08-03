package org.atlas.framework.rest.server.core.config;

import org.atlas.framework.rest.server.core.context.CurrentUserFilter;
import org.atlas.framework.rest.server.core.logging.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private static final String API_PATH_SERVLET_PATTERN = "/api/*";

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilterRegistration() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns(API_PATH_SERVLET_PATTERN);
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CurrentUserFilter> currentUserFilterFilterRegistration() {
        FilterRegistrationBean<CurrentUserFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CurrentUserFilter());
        registrationBean.addUrlPatterns(API_PATH_SERVLET_PATTERN);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
