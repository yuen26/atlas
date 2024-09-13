package org.atlas.business.product.application.event.remote.choreography;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.choreography.OrderCreatedEvent;
import org.atlas.framework.event.contract.order.choreography.QuantityReservedEvent;
import org.atlas.framework.event.contract.order.choreography.ReserveQuantityFailedEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
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
