package org.atlas.framework.notification.consumer.activemq;

import jakarta.jms.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.atlas.framework.notification.core.NotificationDispatcher;
import org.atlas.framework.notification.core.NotificationHandler;
import org.atlas.shared.util.json.JacksonOps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.List;

@Configuration
@EnableJms
@RequiredArgsConstructor
public class ActivemqConsumerConfig {

    public static final String NOTIFICATION_QUEUE = "notification";

    @Bean
    public NotificationDispatcher notificationDispatcher(List<NotificationHandler> handlers) {
        return new NotificationDispatcher(handlers);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-3");
        // true: using JMS topic, false: using JMS queue
        factory.setPubSubDomain(false);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(JacksonOps.objectMapper);
        return converter;
    }
}
