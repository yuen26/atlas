package org.atlas.framework.realtime.sse.service;

import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.realtime.contract.RealtimeService;
import org.atlas.framework.realtime.sse.controller.SseController;
import org.springframework.stereotype.Component;

@Component
public class SseRealtimeService implements RealtimeService {

    private final SseController sseController;

    public SseRealtimeService(SseController sseController) {
        this.sseController = sseController;
    }

    @Override
    public void publish(DomainEvent event) {
        sseController.send(event);
    }
}
