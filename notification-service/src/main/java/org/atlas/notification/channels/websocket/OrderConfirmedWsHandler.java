package org.atlas.notification.channels.websocket;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.OrderConfirmedEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.notification.websocket.OrderStatusNotificationWsService;
import org.atlas.notification.model.OrderStatusDto;
import org.atlas.order.domain.shared.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConfirmedWsHandler implements EventHandler<OrderConfirmedEvent> {

    private final OrderStatusNotificationWsService wsService;

    @Override
    public EventType supports() {
        return EventType.ORDER_CONFIRMED;
    }

    @Override
    public void handle(OrderConfirmedEvent event) {
        OrderStatusDto payload = new OrderStatusDto(OrderStatus.CONFIRMED);
        wsService.notify(event.getOrder().getId(), payload);
    }
}
