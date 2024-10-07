package org.atlas.framework.notification.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * This config is useful when you just need to send messages to client one way.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketServerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // The "/topic" prefix is for broadcasting messages to subscribers
        config.enableSimpleBroker(Constants.DESTINATION_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Client connects to this endpoint
        registry.addEndpoint(Constants.STOMP_ENDPOINT)
            .setAllowedOrigins("*")  // Allow specific origin
            .withSockJS();
    }
}
