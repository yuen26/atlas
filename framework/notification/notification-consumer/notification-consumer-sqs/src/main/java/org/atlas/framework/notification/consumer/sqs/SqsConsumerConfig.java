package org.atlas.framework.notification.consumer.sqs;

import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.notification.core.NotificationDispatcher;
import org.atlas.framework.notification.core.NotificationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.util.List;

@Configuration
@Slf4j
public class SqsConsumerConfig {

    @Bean
    public NotificationDispatcher notificationDispatcher(List<NotificationHandler> handlers) {
        return new NotificationDispatcher(handlers);
    }

    @Bean
    public SqsClient sqsClient() {
        SqsClient sqsClient = SqsClient.builder()
            .build();
        log.info("Initialized SQS client");
        return sqsClient;
    }
}
