package org.atlas.framework.rest.server.core.config;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.rest.server.core.converter.StringToFileTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final StringToFileTypeConverter stringToFileTypeConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToFileTypeConverter);
    }
}
