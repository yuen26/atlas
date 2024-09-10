package org.atlas.framework.notification.websocket.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketService {

    private static final String DESTINATION_PREFIX = "/topic/";

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void send(Object payload) {
        String destination = DESTINATION_PREFIX + "test";
        messagingTemplate.convertAndSend(destination, payload);
    }
}
