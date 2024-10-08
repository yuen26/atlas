package org.atlas.order.application.event.orchestration;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.OrderCanceledEvent;
import org.atlas.framework.event.core.contract.order.orchestration.ReserveCreditRequestEvent;
import org.atlas.framework.event.core.contract.order.orchestration.ReserveQuantityReplyEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.atlas.order.application.contract.model.OrderDto;
import org.atlas.order.application.service.OrderService;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.repository.OrderRepository;
import org.atlas.order.domain.shared.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReserveQuantityReplyEventHandler implements EventHandler<ReserveQuantityReplyEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public EventType supports() {
        return EventType.RESERVE_QUANTITY_REPLY;
    }

    @Override
    public void handle(ReserveQuantityReplyEvent reserveQuantityReplyEvent) {
        OrderDto orderDto = reserveQuantityReplyEvent.getOrder();
        Order order = orderService.findPendingOrder(orderDto.getId());
        if (reserveQuantityReplyEvent.isSuccess()) {
            ReserveCreditRequestEvent reserveCreditRequestEvent = new ReserveCreditRequestEvent(orderDto);
            eventPublisherTemplate.publish(reserveCreditRequestEvent);
        } else {
            order.setStatus(OrderStatus.CANCELED);
            orderRepository.update(order);

            orderDto.setStatus(OrderStatus.CANCELED);
            orderDto.setCanceledReason(reserveQuantityReplyEvent.getError());
            OrderCanceledEvent orderCanceledEvent = new OrderCanceledEvent(orderDto);
            eventPublisherTemplate.publish(orderCanceledEvent);
        }
    }
}
