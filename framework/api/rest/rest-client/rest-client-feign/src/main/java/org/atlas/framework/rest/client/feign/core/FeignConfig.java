package org.atlas.framework.rest.client.feign.core;

import feign.Logger;
import feign.Request;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Need to enable feign client via {@link org.springframework.cloud.openfeign.EnableFeignClients} as well.
 */
@Configuration
@EnableFeignClients(basePackages = "org.atlas.framework.rest.client.feign.client")
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }

    @Bean
    public Request.Options feignRequestOptions() {
        return new Request.Options(30, TimeUnit.SECONDS, 30, TimeUnit.SECONDS, true);
    }
}
