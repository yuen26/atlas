package org.atlas.order.application.event.orchestration;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.OrderCanceledEvent;
import org.atlas.framework.event.core.contract.order.OrderConfirmedEvent;
import org.atlas.framework.event.core.contract.order.orchestration.ReserveCreditReplyEvent;
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
public class ReserveCreditReplyEventHandler implements EventHandler<ReserveCreditReplyEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public EventType supports() {
        return EventType.RESERVE_CREDIT_REPLY;
    }

    @Override
    public void handle(ReserveCreditReplyEvent reserveCreditReplyEvent) {
        OrderDto orderDto = reserveCreditReplyEvent.getOrder();
        Order order = orderService.findPendingOrder(orderDto.getId());
        if (reserveCreditReplyEvent.isSuccess()) {
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.update(order);

            orderDto.setStatus(OrderStatus.CONFIRMED);
            OrderConfirmedEvent orderConfirmedEvent = new OrderConfirmedEvent(orderDto);
            eventPublisherTemplate.publish(orderConfirmedEvent);
        } else {
            order.setStatus(OrderStatus.CANCELED);
            orderRepository.update(order);

            orderDto.setStatus(OrderStatus.CANCELED);
            orderDto.setCanceledReason(reserveCreditReplyEvent.getError());
            OrderCanceledEvent orderCanceledEvent = new OrderCanceledEvent(orderDto);
            eventPublisherTemplate.publish(orderCanceledEvent);
        }
    }
}
