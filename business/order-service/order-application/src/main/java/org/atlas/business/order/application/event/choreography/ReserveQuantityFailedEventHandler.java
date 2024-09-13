package org.atlas.business.order.application.event.choreography;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.choreography.ReserveQuantityFailedEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReserveQuantityFailedEventHandler implements EventHandler<ReserveQuantityFailedEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    public EventType supports() {
        return EventType.RESERVE_QUANTITY_FAILED;
    }

    @Override
    @Transactional
    public void handle(ReserveQuantityFailedEvent event) {
        Order order = orderService.findPendingOrder(event.getOrder().getId());
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.update(order);
    }
}
