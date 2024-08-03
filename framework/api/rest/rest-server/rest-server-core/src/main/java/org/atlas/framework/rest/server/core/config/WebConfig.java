package org.atlas.framework.rest.server.core.config;

import org.atlas.framework.rest.server.core.context.CurrentUserHandlerMethodArgumentResolver;
import org.atlas.framework.rest.server.core.converter.StringToFileTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;
    private final StringToFileTypeConverter stringToFileTypeConverter;

    public WebConfig(CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver,
                     StringToFileTypeConverter stringToFileTypeConverter) {
        this.currentUserHandlerMethodArgumentResolver = currentUserHandlerMethodArgumentResolver;
        this.stringToFileTypeConverter = stringToFileTypeConverter;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserHandlerMethodArgumentResolver);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToFileTypeConverter);
    }
}
