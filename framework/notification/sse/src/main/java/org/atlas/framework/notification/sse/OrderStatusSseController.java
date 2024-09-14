package org.atlas.framework.notification.sse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("sse")
@RequiredArgsConstructor
@Slf4j
public class OrderStatusSseController {

    private final OrderStatusNotificationSseService notificationService;

    @GetMapping("/orders/{orderId}/status")
    public SseEmitter streamNotification(@PathVariable("orderId") Integer orderId) {
        return notificationService.subscribe(orderId);
    }
}
