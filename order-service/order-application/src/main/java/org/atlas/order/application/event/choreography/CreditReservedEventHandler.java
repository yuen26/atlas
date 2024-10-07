package org.atlas.order.application.event.choreography;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.OrderConfirmedEvent;
import org.atlas.framework.event.core.contract.order.choreography.CreditReservedEvent;
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
public class CreditReservedEventHandler implements EventHandler<CreditReservedEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public EventType supports() {
        return EventType.CREDIT_RESERVED;
    }

    @Override
    @Transactional
    public void handle(CreditReservedEvent creditReservedEvent) {
        OrderDto orderDto = creditReservedEvent.getOrder();
        Order order = orderService.findPendingOrder(orderDto.getId());
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.update(order);

        orderDto.setStatus(OrderStatus.CONFIRMED);
        OrderConfirmedEvent orderConfirmedEvent = new OrderConfirmedEvent(orderDto);
        eventPublisherTemplate.publish(orderConfirmedEvent);
    }
}
