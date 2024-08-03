package org.atlas.framework.notification.publisher.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
@Slf4j
public class SqsPublisherConfig {

    @Bean
    public SqsClient sqsClient() {
        SqsClient sqsClient = SqsClient.builder()
            .build();
        log.info("Initialized SQS client");
        return sqsClient;
    }
}
