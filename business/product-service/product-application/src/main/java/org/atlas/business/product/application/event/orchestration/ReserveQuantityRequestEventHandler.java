package org.atlas.business.product.application.event.orchestration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.commons.utils.ConcurrencyUtil;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.orchestration.ReserveQuantityReplyEvent;
import org.atlas.framework.event.contract.order.orchestration.ReserveQuantityRequestEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
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
    public EventType supports() {
        return EventType.RESERVE_QUANTITY_REQUEST;
    }

    @Override
    @Transactional
    public void handle(ReserveQuantityRequestEvent requestEvent) {
        OrderDto order = requestEvent.getOrder();
        ReserveQuantityReplyEvent replyEvent = new ReserveQuantityReplyEvent();
        replyEvent.setOrder(order);
        try {
            order.getOrderItems().forEach(orderItem -> {
                ConcurrencyUtil.sleep(3, 5);
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
