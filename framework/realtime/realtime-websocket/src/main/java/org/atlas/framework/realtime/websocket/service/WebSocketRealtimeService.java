package org.atlas.framework.realtime.websocket.service;

import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.realtime.contract.RealtimeService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketRealtimeService implements RealtimeService {

    private static final String DESTINATION_PREFIX = "/topic/";

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketRealtimeService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        String destination = dispatchDestination(event);
        messagingTemplate.convertAndSend(destination, event);
    }

    private String dispatchDestination(DomainEvent event) {
        switch (event.type()) {
            case ORDER_CONFIRMED -> {
                return DESTINATION_PREFIX + "order";
            }
            default -> throw new UnsupportedOperationException("Unsupported event type");
        }
    }
}
