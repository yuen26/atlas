package org.atlas.business.product.application.event.remote.orchestration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.event.contract.order.model.OrderData;
import org.atlas.framework.event.contract.order.orchestration.ReserveQuantityReplyEvent;
import org.atlas.framework.event.contract.order.orchestration.ReserveQuantityRequestEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReserveQuantityRequestEventHandler implements EventHandler<ReserveQuantityRequestEvent> {

    private final ProductRepository productRepository;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    @Transactional
    public void handle(ReserveQuantityRequestEvent requestEvent) {
        OrderData orderData = requestEvent.getOrder();
        ReserveQuantityReplyEvent replyEvent = new ReserveQuantityReplyEvent();
        replyEvent.setOrder(orderData);
        try {
            orderData.getOrderItems().forEach(orderItem -> {
                int updated = productRepository.decreaseQuantity(orderItem.getProductId(), orderItem.getQuantity());
                if (updated == 1) {
                    log.info("Reserved quantity: productId={}, amount={}, eventId={}",
                        orderItem.getProductId(), orderItem.getQuantity(), requestEvent.getEventId());
                } else {
                    log.error("Failed to reserve quantity: productId={}, amount={}, eventId={}",
                        orderItem.getProductId(), orderItem.getQuantity(), requestEvent.getEventId());
                    throw new RuntimeException(String.format("Product %d has insufficient quantity", orderItem.getProductId()));
                }
            });
            replyEvent.setSuccess(true);
        } catch (Exception e) {
            replyEvent.setSuccess(false);
            replyEvent.setError(e.getMessage());
        }
        eventPublisherTemplate.publish(replyEvent);
    }
}
