package org.atlas.framework.notification.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class OrderStatusNotificationSseService {

    private final ConcurrentHashMap<Integer, SseEmitter> clients = new ConcurrentHashMap<>();

    public SseEmitter subscribe(Integer orderId) {
        SseEmitter emitter = new SseEmitter();
        clients.put(orderId, emitter);

        emitter.onCompletion(() -> clients.remove(orderId));
        emitter.onTimeout(() -> clients.remove(orderId));

        return emitter;
    }

    public void notify(Integer orderId, Object payload) {
        log.info("[SSE] Notifying the status of order {}", orderId);
        SseEmitter emitter = clients.get(orderId);
        if (emitter != null) {
            try {
                SseEmitter.SseEventBuilder eventBuilder = SseEmitter.event()
                    .name("orderStatus")
                    .data(payload);
                emitter.send(eventBuilder);
            } catch (IOException e) {
                log.error("[SSE] Failed to notify the status of order {}", orderId, e);
                emitter.completeWithError(e);
            }
        } else {
            log.error("[SSE] Failed to notify the status of order {}: Not found SseEmitter", orderId);
        }
    }
}
