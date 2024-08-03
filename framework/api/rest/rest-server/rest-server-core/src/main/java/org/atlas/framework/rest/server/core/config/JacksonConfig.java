package org.atlas.framework.rest.server.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atlas.shared.util.json.JacksonOps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return JacksonOps.objectMapper;
    }
}
