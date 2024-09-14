package org.atlas.business.order.application.event.orchestration;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.OrderCanceledEvent;
import org.atlas.framework.event.contract.order.OrderConfirmedEvent;
import org.atlas.framework.event.contract.order.orchestration.ReserveCreditReplyEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
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
