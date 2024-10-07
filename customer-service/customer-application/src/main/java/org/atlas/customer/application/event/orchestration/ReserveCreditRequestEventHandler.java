package org.atlas.customer.application.event.orchestration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.commons.utils.ConcurrencyUtil;
import org.atlas.customer.domain.repository.CustomerRepository;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.orchestration.ReserveCreditReplyEvent;
import org.atlas.framework.event.core.contract.order.orchestration.ReserveCreditRequestEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.atlas.order.application.contract.model.OrderDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReserveCreditRequestEventHandler implements EventHandler<ReserveCreditRequestEvent> {

    private final CustomerRepository customerRepository;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public EventType supports() {
        return EventType.RESERVE_CREDIT_REQUEST;
    }

    @Override
    @Transactional
    public void handle(ReserveCreditRequestEvent requestEvent) {
        ConcurrencyUtil.sleep(3, 5);
        OrderDto order = requestEvent.getOrder();
        ReserveCreditReplyEvent replyEvent = new ReserveCreditReplyEvent();
        replyEvent.setOrder(order);
        int reserveCreditResult = customerRepository.decreaseCredit(order.getCustomer().getUserId(), order.getAmount());
        if (reserveCreditResult == 1) {
            log.info("Reserved credit: userId={}, amount={}, eventId={}",
                order.getCustomer().getUserId(), order.getAmount(), requestEvent.getEventId());
            replyEvent.setSuccess(true);
        } else {
            log.error("Failed to reserve credit: userId={}, amount={}, eventId={}",
                order.getCustomer().getUserId(), order.getAmount(), requestEvent.getEventId());
            replyEvent.setSuccess(false);
            replyEvent.setError("Customer has insufficient credit");
        }
        eventPublisherTemplate.publish(replyEvent);
    }
}
