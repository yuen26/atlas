package org.atlas.business.user.application.event.choreography;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.choreography.CreditReservedEvent;
import org.atlas.framework.event.contract.order.choreography.QuantityReservedEvent;
import org.atlas.framework.event.contract.order.choreography.ReserveCreditFailedEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
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
    public EventType supports() {
        return EventType.QUANTITY_RESERVED;
    }

    @Override
    @Transactional
    public void handle(QuantityReservedEvent quantityReservedEvent) {
        OrderDto order = quantityReservedEvent.getOrder();
        int reserveCreditResult = userRepository.decreaseCredit(order.getCustomer().getId(), order.getAmount());
        if (reserveCreditResult == 1) {
            log.info("Reserved credit: customerId={}, amount={}, eventId={}",
                order.getCustomer().getId(), order.getAmount(), quantityReservedEvent.getEventId());
            CreditReservedEvent creditReservedEvent = new CreditReservedEvent(order);
            eventPublisherTemplate.publish(creditReservedEvent);
        } else {
            log.error("Failed to reserve credit: customerId={}, amount={}, eventId={}",
                order.getCustomer().getId(), order.getAmount(), quantityReservedEvent.getEventId());
            ReserveCreditFailedEvent reserveCreditFailedEvent =
                new ReserveCreditFailedEvent(order, "Failed to reserve credit");
            eventPublisherTemplate.publish(reserveCreditFailedEvent);
        }
    }
}
