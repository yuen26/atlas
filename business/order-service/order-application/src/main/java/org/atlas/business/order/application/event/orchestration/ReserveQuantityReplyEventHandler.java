package org.atlas.business.order.application.event.orchestration;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.orchestration.ReserveCreditRequestEvent;
import org.atlas.framework.event.contract.order.orchestration.ReserveQuantityReplyEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReserveQuantityReplyEventHandler implements EventHandler<ReserveQuantityReplyEvent> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public EventType supports() {
        return EventType.RESERVE_QUANTITY_REPLY;
    }

    @Override
    public void handle(ReserveQuantityReplyEvent reserveQuantityReplyEvent) {
        Order order = orderService.findPendingOrder(reserveQuantityReplyEvent.getOrder().getId());
        if (reserveQuantityReplyEvent.isSuccess()) {
            ReserveCreditRequestEvent reserveCreditRequestEvent =
                new ReserveCreditRequestEvent(reserveQuantityReplyEvent.getOrder());
            eventPublisherTemplate.publish(reserveCreditRequestEvent);
        } else {
            order.setStatus(OrderStatus.CANCELED);
            orderRepository.update(order);
        }
    }
}
