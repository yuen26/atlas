package org.atlas.customer.application.event.choreography;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.commons.utils.ConcurrencyUtil;
import org.atlas.customer.domain.repository.CustomerRepository;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.choreography.CreditReservedEvent;
import org.atlas.framework.event.core.contract.order.choreography.QuantityReservedEvent;
import org.atlas.framework.event.core.contract.order.choreography.ReserveCreditFailedEvent;
import org.atlas.framework.event.core.handler.EventHandler;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.atlas.order.application.contract.model.OrderDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuantityReservedEventHandler implements EventHandler<QuantityReservedEvent> {

    private final CustomerRepository customerRepository;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Override
    public EventType supports() {
        return EventType.QUANTITY_RESERVED;
    }

    @Override
    @Transactional
    public void handle(QuantityReservedEvent quantityReservedEvent) {
        ConcurrencyUtil.sleep(3, 5);
        OrderDto order = quantityReservedEvent.getOrder();
        int reserveCreditResult = customerRepository.decreaseCredit(order.getCustomer().getUserId(), order.getAmount());
        if (reserveCreditResult == 1) {
            log.info("Reserved credit: userId={}, amount={}, eventId={}",
                order.getCustomer().getUserId(), order.getAmount(), quantityReservedEvent.getEventId());
            CreditReservedEvent creditReservedEvent = new CreditReservedEvent(order);
            eventPublisherTemplate.publish(creditReservedEvent);
        } else {
            log.error("Failed to reserve credit: userId={}, amount={}, eventId={}",
                order.getCustomer().getUserId(), order.getAmount(), quantityReservedEvent.getEventId());
            ReserveCreditFailedEvent reserveCreditFailedEvent =
                new ReserveCreditFailedEvent(order, "Failed to reserve credit");
            eventPublisherTemplate.publish(reserveCreditFailedEvent);
        }
    }
}
