package org.atlas.business.order.application.event.choreography;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.OrderConfirmedEvent;
import org.atlas.framework.event.contract.order.choreography.CreditReservedEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
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
