package org.atlas.business.order.application.event.choreography;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.choreography.ReserveCreditFailedEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReserveCreditFailedEventHandler implements EventHandler<ReserveCreditFailedEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    public EventType supports() {
        return EventType.RESERVE_CREDIT_FAILED;
    }

    @Override
    @Transactional
    public void handle(ReserveCreditFailedEvent event) {
        Order order = orderService.findPendingOrder(event.getOrder().getId());
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.update(order);
    }
}
