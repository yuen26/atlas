package org.atlas.business.order.application.event.remote.choreography;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.order.OrderConfirmedEvent;
import org.atlas.framework.event.contract.order.choreography.CreditReservedEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreditReservedEventHandler implements EventHandler<CreditReservedEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public void handle(CreditReservedEvent creditReservedEvent) {
        Order order = orderService.findPendingOrder(creditReservedEvent.getOrder().getOrderId());
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.update(order);

        OrderConfirmedEvent orderConfirmedEvent = new OrderConfirmedEvent(creditReservedEvent.getOrder());
        applicationEventPublisher.publishEvent(orderConfirmedEvent);
    }
}
