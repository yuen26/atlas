package org.atlas.framework.notification.publisher.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atlas.commons.utils.json.JacksonOps;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitPublisherConfig {

    public static final String NOTIFICATION_QUEUE = "notification";
    public static final String NOTIFICATION_RETRY_QUEUE = "notification_retry";
    public static final String NOTIFICATION_DLQ_QUEUE = "notification_dlq";
    public static final String NOTIFICATION_EXCHANGE = "notification";
    public static final String NOTIFICATION_RETRY_EXCHANGE = "notification_retry";
    public static final String NOTIFICATION_DLQ_EXCHANGE = "notification_dlq";
    public static final String NOTIFICATION_ROUTING_KEY = "notification";
    public static final String NOTIFICATION_RETRY_ROUTING_KEY = "notification_retry";
    public static final String NOTIFICATION_DLQ_ROUTING_KEY = "notification_dlq";

    private static final int DEFAULT_DELAY = 5000; // 5 seconds

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

    @Bean
    public Queue notificationQueue() {
        return QueueBuilder.durable(NOTIFICATION_QUEUE)
                .withArgument("x-dead-letter-exchange", NOTIFICATION_RETRY_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", NOTIFICATION_RETRY_QUEUE)
                .quorum()
                .build();
    }

    @Bean
    public Queue notificationRetryQueue() {
        return QueueBuilder.durable(NOTIFICATION_RETRY_QUEUE)
                .withArgument("x-dead-letter-exchange", NOTIFICATION_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", NOTIFICATION_QUEUE)
                .ttl(DEFAULT_DELAY)
                .quorum()
                .build();
    }

    @Bean
    public Queue notificationDlqQueue() {
        return QueueBuilder.durable(NOTIFICATION_DLQ_QUEUE)
                .quorum()
                .build();
    }

    @Bean
    public DirectExchange notificationExchange() {
        return new DirectExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public DirectExchange notificationRetryExchange() {
        return new DirectExchange(NOTIFICATION_RETRY_EXCHANGE);
    }

    @Bean
    public DirectExchange notificationDlqExchange() {
        return new DirectExchange(NOTIFICATION_DLQ_EXCHANGE);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, DirectExchange notificationExchange) {
        return BindingBuilder.bind(notificationQueue)
            .to(notificationExchange)
            .with(NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public Binding notificationRetryBinding(Queue notificationRetryQueue, DirectExchange notificationRetryExchange) {
        return BindingBuilder.bind(notificationRetryQueue)
            .to(notificationRetryExchange)
            .with(NOTIFICATION_RETRY_ROUTING_KEY);
    }

    @Bean
    public Binding notificationDlqBinding(Queue notificationDlqQueue, DirectExchange notificationDlqExchange) {
        return BindingBuilder.bind(notificationDlqQueue)
            .to(notificationDlqExchange)
            .with(NOTIFICATION_DLQ_ROUTING_KEY);
    }
}
