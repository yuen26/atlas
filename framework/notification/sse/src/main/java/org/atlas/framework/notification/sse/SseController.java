package org.atlas.framework.notification.sse;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.atlas.commons.utils.idgenerator.UUIDGenerator;
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

    public void send(Object payload) {
        SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
            .id(UUIDGenerator.generate())
            .name("Test")
            .data(payload);
        try {
            orderSseEmitter.send(sseEventBuilder);
        } catch (IOException e) {
            log.error("Failed to send", e);
        }
    }
}
