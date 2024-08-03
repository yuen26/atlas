package org.atlas.framework.realtime.sse.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.shared.util.IdGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("sse")
@Slf4j
public class SseController {

    private SseEmitter orderSseEmitter;

    @PostConstruct
    public void init() {
        orderSseEmitter = new SseEmitter();
    }

    @GetMapping("/orders")
    public SseEmitter orderSseEmitter() {
        return orderSseEmitter;
    }

    public void send(DomainEvent event) {
        SseEmitter sseEmitter = dispatchSseEmitter(event);
        SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
            .id(IdGenerator.uuid())
            .name(event.type().name())
            .data(event);
        try {
            sseEmitter.send(sseEventBuilder);
        } catch (IOException e) {
            log.error("Failed to send event {}", event, e);
        }
    }

    private SseEmitter dispatchSseEmitter(DomainEvent event) {
        switch (event.type()) {
            case ORDER_CONFIRMED -> {
                return orderSseEmitter;
            }
            default -> throw new UnsupportedOperationException("Unsupported event type");
        }
    }
}
