package org.atlas.business.order.application.event.remote.choreography;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.order.choreography.ReserveQuantityFailedEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReserveQuantityFailedEventHandler implements EventHandler<ReserveQuantityFailedEvent> {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void handle(ReserveQuantityFailedEvent event) {
        Order order = orderRepository.findById(event.getOrder().getOrderId())
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));
        order.setStatus(OrderStatus.CANCELED);
        order.setCanceledReason(event.getError());
        orderRepository.update(order);
    }
}
