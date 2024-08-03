package org.atlas.business.user.application.event.remote.orchestration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.framework.event.contract.order.model.OrderData;
import org.atlas.framework.event.contract.order.orchestration.ReserveCreditReplyEvent;
import org.atlas.framework.event.contract.order.orchestration.ReserveCreditRequestEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReserveCreditRequestEventHandler implements EventHandler<ReserveCreditRequestEvent> {

    private final UserRepository userRepository;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    @Transactional
    public void handle(ReserveCreditRequestEvent requestEvent) {
        OrderData orderData = requestEvent.getOrder();
        ReserveCreditReplyEvent replyEvent = new ReserveCreditReplyEvent();
        replyEvent.setOrder(orderData);
        int reserveCreditResult = userRepository.decreaseCredit(orderData.getCustomerId(), orderData.getAmount());
        if (reserveCreditResult == 1) {
            log.info("Reserved credit: customerId={}, amount={}, eventId={}",
                orderData.getCustomerId(), orderData.getAmount(), requestEvent.getEventId());
            replyEvent.setSuccess(true);
        } else {
            log.error("Failed to reserve credit: customerId={}, amount={}, eventId={}",
                orderData.getCustomerId(), orderData.getAmount(), requestEvent.getEventId());
            replyEvent.setSuccess(false);
            replyEvent.setError("Customer has insufficient credit");
        }
        eventPublisherTemplate.publish(replyEvent);
    }
}
