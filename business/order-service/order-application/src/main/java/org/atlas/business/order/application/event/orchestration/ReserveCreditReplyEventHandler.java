package org.atlas.business.order.application.event.orchestration;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.EventType;
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
        Order order = orderService.findPendingOrder(reserveCreditReplyEvent.getOrder().getId());
        if (reserveCreditReplyEvent.isSuccess()) {
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.update(order);

            OrderConfirmedEvent orderConfirmedEvent = new OrderConfirmedEvent(reserveCreditReplyEvent.getOrder());
            eventPublisherTemplate.publish(orderConfirmedEvent);
        } else {
            order.setStatus(OrderStatus.CANCELED);
            orderRepository.update(order);
        }
    }
}
