package org.atlas.business.order.application.event.remote.orchestration;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.order.orchestration.ReserveCreditRequestEvent;
import org.atlas.framework.event.contract.order.orchestration.ReserveQuantityReplyEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReserveQuantityReplyEventHandler implements EventHandler<ReserveQuantityReplyEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public void handle(ReserveQuantityReplyEvent reserveQuantityReplyEvent) {
        Order order = orderService.findPendingOrder(reserveQuantityReplyEvent.getOrder().getOrderId());
        if (reserveQuantityReplyEvent.isSuccess()) {
            ReserveCreditRequestEvent reserveCreditRequestEvent =
                new ReserveCreditRequestEvent(reserveQuantityReplyEvent.getOrder());
            eventPublisherTemplate.publish(reserveCreditRequestEvent);
        } else {
            order.setStatus(OrderStatus.CANCELED);
            order.setCanceledReason(reserveQuantityReplyEvent.getError());
            orderRepository.update(order);
        }
    }
}
