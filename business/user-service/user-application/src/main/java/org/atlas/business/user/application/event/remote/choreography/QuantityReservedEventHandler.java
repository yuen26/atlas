package org.atlas.business.user.application.event.remote.choreography;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.framework.event.contract.order.choreography.CreditReservedEvent;
import org.atlas.framework.event.contract.order.choreography.QuantityReservedEvent;
import org.atlas.framework.event.contract.order.choreography.ReserveCreditFailedEvent;
import org.atlas.framework.event.contract.order.model.OrderData;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuantityReservedEventHandler implements EventHandler<QuantityReservedEvent> {

    private final UserRepository userRepository;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    @Transactional
    public void handle(QuantityReservedEvent quantityReservedEvent) {
        OrderData orderData = quantityReservedEvent.getOrder();
        int reserveCreditResult = userRepository.decreaseCredit(orderData.getCustomerId(), orderData.getAmount());
        if (reserveCreditResult == 1) {
            log.info("Reserved credit: customerId={}, amount={}, eventId={}",
                orderData.getCustomerId(), orderData.getAmount(), quantityReservedEvent.getEventId());
            CreditReservedEvent creditReservedEvent = new CreditReservedEvent(orderData);
            eventPublisherTemplate.publish(creditReservedEvent);
        } else {
            log.error("Failed to reserve credit: customerId={}, amount={}, eventId={}",
                orderData.getCustomerId(), orderData.getAmount(), quantityReservedEvent.getEventId());
            ReserveCreditFailedEvent reserveCreditFailedEvent =
                new ReserveCreditFailedEvent(orderData, "Failed to reserve credit");
            eventPublisherTemplate.publish(reserveCreditFailedEvent);
        }
    }
}
