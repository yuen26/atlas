package org.atlas.framework.notification.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderStatusNotificationWsService {

    private final SimpMessagingTemplate messagingTemplate;

    public void notify(Integer orderId, Object payload) {
        log.info("[WS] Notifying the status of order {}", orderId);
        messagingTemplate.convertAndSend(Constants.DESTINATION_PREFIX + "/order/" + orderId, payload);
    }
}
