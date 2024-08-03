package org.atlas.framework.notification.consumer.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atlas.framework.notification.core.NotificationDispatcher;
import org.atlas.framework.notification.core.NotificationHandler;
import org.atlas.shared.util.json.JacksonOps;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitConsumerConfig {

    public static final String NOTIFICATION_QUEUE = "notification";
    public static final String NOTIFICATION_DLQ_QUEUE = "notification_dlq";
    public static final String NOTIFICATION_DLQ_ROUTING_KEY = "notification_dlq";

    @Bean
    public NotificationDispatcher notificationDispatcher(List<NotificationHandler> handlers) {
        return new NotificationDispatcher(handlers);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = JacksonOps.objectMapper;
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
