package org.atlas.business.product.application.event.choreography;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.commons.utils.ConcurrencyUtil;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.choreography.ReserveCreditFailedEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReserveCreditFailedEventHandler implements EventHandler<ReserveCreditFailedEvent> {

    private final ProductRepository productRepository;

    @Override
    public EventType supports() {
        return EventType.RESERVE_CREDIT_FAILED;
    }

    @Override
    @Transactional
    public void handle(ReserveCreditFailedEvent event) {
        event.getOrder()
            .getOrderItems()
            .forEach(orderItem -> {
                ConcurrencyUtil.sleep(3, 5);
                int updated = productRepository.increaseQuantity(orderItem.getProductId(), orderItem.getQuantity());
                if (updated == 1) {
                    log.info("Rollback reserved quantity: productId={}, amount={}, eventId={}",
                        orderItem.getProductId(), orderItem.getQuantity(), event.getEventId());
                } else {
                    log.error("Failed to rollback reserved quantity: productId={}, amount={}, eventId={}",
                        orderItem.getProductId(), orderItem.getQuantity(), event.getEventId());
                }
            });
    }
}
