package org.atlas.order.application.event.choreography;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.OrderCanceledEvent;
import org.atlas.framework.event.core.contract.order.choreography.ReserveCreditFailedEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.atlas.order.application.contract.model.OrderDto;
import org.atlas.order.application.service.OrderService;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.repository.OrderRepository;
import org.atlas.order.domain.shared.enums.OrderStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReserveCreditFailedEventHandler implements EventHandler<ReserveCreditFailedEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public EventType supports() {
        return EventType.RESERVE_CREDIT_FAILED;
    }

    @Override
    @Transactional
    public void handle(ReserveCreditFailedEvent event) {
        OrderDto orderDto = event.getOrder();
        Order order = orderService.findPendingOrder(orderDto.getId());
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.update(order);

        orderDto.setStatus(OrderStatus.CANCELED);
        orderDto.setCanceledReason(event.getError());
        OrderCanceledEvent orderCanceledEvent = new OrderCanceledEvent(orderDto);
        eventPublisherTemplate.publish(orderCanceledEvent);
    }
}
