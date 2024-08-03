package org.atlas.business.product.application.event.remote.choreography;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.event.contract.order.choreography.OrderCreatedEvent;
import org.atlas.framework.event.contract.order.choreography.QuantityReservedEvent;
import org.atlas.framework.event.contract.order.choreography.ReserveQuantityFailedEvent;
import org.atlas.framework.event.contract.order.model.OrderData;
import org.atlas.framework.event.core.handler.EventHandler;
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
    @Transactional
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        OrderData orderData = orderCreatedEvent.getOrder();
        try {
            orderData.getOrderItems().forEach(orderItem -> {
                Product product = productRepository.findById(orderItem.getProductId())
                    .orElseThrow(() ->
                        new RuntimeException(String.format("Product %d not found", orderItem.getProductId())));
                int updated = productRepository.decreaseQuantity(product.getId(), orderItem.getQuantity());
                if (updated == 1) {
                    log.info("Reserved quantity: productId={}, amount={}, eventId={}",
                        product.getId(), orderItem.getQuantity(), orderCreatedEvent.getEventId());
                } else {
                    log.error("Failed to reserve quantity: productId={}, amount={}, eventId={}",
                        product.getId(), orderItem.getQuantity(), orderCreatedEvent.getEventId());
                    throw new RuntimeException(String.format("Product %d has insufficient quantity", orderItem.getProductId()));
                }
            });
            QuantityReservedEvent quantityReservedEvent = new QuantityReservedEvent(orderData);
            eventPublisherTemplate.publish(quantityReservedEvent);
        } catch (Exception e) {
            ReserveQuantityFailedEvent reserveQuantityFailedEvent = new ReserveQuantityFailedEvent(orderData, e.getMessage());
            reserveQuantityFailedEvent.setError(e.getMessage());
            eventPublisherTemplate.publish(reserveQuantityFailedEvent);
        }
    }
}
