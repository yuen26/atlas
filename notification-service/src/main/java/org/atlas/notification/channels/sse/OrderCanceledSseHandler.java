package org.atlas.notification.channels.sse;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.OrderCanceledEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.notification.sse.OrderStatusNotificationSseService;
import org.atlas.notification.model.OrderStatusDto;
import org.atlas.order.domain.shared.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCanceledSseHandler implements EventHandler<OrderCanceledEvent> {

    private final OrderStatusNotificationSseService sseService;

    @Override
    public EventType supports() {
        return EventType.ORDER_CANCELED;
    }

    @Override
    public void handle(OrderCanceledEvent event) {
        OrderStatusDto payload = new OrderStatusDto(OrderStatus.CANCELED, event.getOrder().getCanceledReason());
        sseService.notify(event.getOrder().getId(), payload);
    }
}
