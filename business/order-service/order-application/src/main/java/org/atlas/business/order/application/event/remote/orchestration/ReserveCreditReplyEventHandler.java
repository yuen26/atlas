package org.atlas.business.order.application.event.remote.orchestration;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.order.OrderConfirmedEvent;
import org.atlas.framework.event.contract.order.orchestration.ReserveCreditReplyEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReserveCreditReplyEventHandler implements EventHandler<ReserveCreditReplyEvent> {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void handle(ReserveCreditReplyEvent reserveCreditReplyEvent) {
        Order order = orderRepository.findById(reserveCreditReplyEvent.getOrder().getOrderId())
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));
        if (reserveCreditReplyEvent.isSuccess()) {
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.update(order);

            OrderConfirmedEvent orderConfirmedEvent = new OrderConfirmedEvent(reserveCreditReplyEvent.getOrder());
            applicationEventPublisher.publishEvent(orderConfirmedEvent);
        } else {
            order.setStatus(OrderStatus.CANCELED);
            order.setCanceledReason(reserveCreditReplyEvent.getError());
            orderRepository.update(order);
        }
    }
}
