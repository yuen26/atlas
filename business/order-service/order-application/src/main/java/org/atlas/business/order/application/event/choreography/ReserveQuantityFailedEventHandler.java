package org.atlas.business.order.application.event.choreography;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.OrderCanceledEvent;
import org.atlas.framework.event.contract.order.choreography.ReserveQuantityFailedEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReserveQuantityFailedEventHandler implements EventHandler<ReserveQuantityFailedEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public EventType supports() {
        return EventType.RESERVE_QUANTITY_FAILED;
    }

    @Override
    @Transactional
    public void handle(ReserveQuantityFailedEvent event) {
        OrderDto orderDto = event.getOrder();
        Order order = orderService.findPendingOrder(event.getOrder().getId());
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.update(order);

        orderDto.setStatus(OrderStatus.CANCELED);
        orderDto.setCanceledReason(event.getError());
        OrderCanceledEvent orderCanceledEvent = new OrderCanceledEvent(orderDto);
        eventPublisherTemplate.publish(orderCanceledEvent);
    }
}
