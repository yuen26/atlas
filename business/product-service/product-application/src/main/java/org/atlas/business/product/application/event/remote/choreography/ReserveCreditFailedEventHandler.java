package org.atlas.business.product.application.event.remote.choreography;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.event.contract.order.choreography.ReserveCreditFailedEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReserveCreditFailedEventHandler implements EventHandler<ReserveCreditFailedEvent> {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void handle(ReserveCreditFailedEvent event) {
        event.getOrder()
            .getOrderItems()
            .forEach(orderItem -> {
                Product product = productRepository.findById(orderItem.getProductId())
                    .orElseThrow(() ->
                        new RuntimeException(String.format("Product %d not found", orderItem.getProductId())));
                productRepository.increaseQuantity(product.getId(), orderItem.getQuantity());
            });
    }
}
