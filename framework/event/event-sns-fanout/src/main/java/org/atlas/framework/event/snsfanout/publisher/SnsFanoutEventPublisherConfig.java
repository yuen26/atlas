package org.atlas.framework.event.snsfanout.publisher;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.event.contract.DomainEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(SnsFanoutEventPublisherConfig.TopicProperties.class)
@Slf4j
public class SnsFanoutEventPublisherConfig {

    @Bean
    public SnsClient snsClient() {
        SnsClient snsClient = SnsClient.builder()
            .build();
        log.info("Initialized SNS client");
        return snsClient;
    }

    @ConfigurationProperties(prefix = "app.event.sns-fanout.publisher")
    @Data
    public static class TopicProperties {

        private Map<String, String> topics = new HashMap<>();

        public String getTopic(DomainEvent event) {
            return topics.get(event.type());
        }
    }
}
