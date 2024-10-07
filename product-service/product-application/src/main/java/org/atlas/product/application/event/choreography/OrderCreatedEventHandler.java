package org.atlas.product.application.event.choreography;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.commons.utils.ConcurrencyUtil;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.choreography.OrderCreatedEvent;
import org.atlas.framework.event.core.contract.order.choreography.QuantityReservedEvent;
import org.atlas.framework.event.core.contract.order.choreography.ReserveQuantityFailedEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.atlas.order.application.contract.model.OrderDto;
import org.atlas.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedEventHandler implements EventHandler<OrderCreatedEvent> {

    private final ProductRepository productRepository;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public EventType supports() {
        return EventType.ORDER_CREATED;
    }

    @Override
    @Transactional
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        OrderDto order = orderCreatedEvent.getOrder();
        try {
            order.getOrderItems().forEach(orderItem -> {
                ConcurrencyUtil.sleep(3, 5);
                int updated = productRepository.decreaseQuantity(orderItem.getProductId(), orderItem.getQuantity());
                if (updated == 1) {
                    log.info("Reserved quantity: productId={}, amount={}, eventId={}",
                        orderItem.getProductId(), orderItem.getQuantity(), orderCreatedEvent.getEventId());
                } else {
                    log.error("Failed to reserve quantity: productId={}, amount={}, eventId={}",
                        orderItem.getProductId(), orderItem.getQuantity(), orderCreatedEvent.getEventId());
                    throw new RuntimeException(String.format("Product %d has insufficient quantity", orderItem.getProductId()));
                }
            });
            QuantityReservedEvent quantityReservedEvent = new QuantityReservedEvent(order);
            eventPublisherTemplate.publish(quantityReservedEvent);
        } catch (Exception e) {
            ReserveQuantityFailedEvent reserveQuantityFailedEvent = new ReserveQuantityFailedEvent(order, e.getMessage());
            reserveQuantityFailedEvent.setError(e.getMessage());
            eventPublisherTemplate.publish(reserveQuantityFailedEvent);
        }
    }
}
