package org.atlas.notification.channels.websocket;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.OrderCanceledEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.notification.websocket.OrderStatusNotificationWsService;
import org.atlas.notification.model.OrderStatusDto;
import org.atlas.order.domain.shared.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCanceledWsHandler implements EventHandler<OrderCanceledEvent> {

    private final OrderStatusNotificationWsService wsService;

    @Override
    public EventType supports() {
        return EventType.ORDER_CANCELED;
    }

    @Override
    public void handle(OrderCanceledEvent event) {
        OrderStatusDto payload = new OrderStatusDto(OrderStatus.CANCELED, event.getOrder().getCanceledReason());
        wsService.notify(event.getOrder().getId(), payload);
    }
}
