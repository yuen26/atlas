package org.atlas.job.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CancelOverduePendingOrdersJobLogic {

    private final OrderRepository orderRepository;

    private static final int TIMEOUT_IN_MINUTES = 15;

    public void execute() {
        Date now = new Date();
        Date dueDate = DateUtils.addMinutes(now, -1 * TIMEOUT_IN_MINUTES);
        List<Order> overduePendingOrders = orderRepository.findByStatusAndCreatedBefore(OrderStatus.PENDING, dueDate);
        log.info("Found {} overdue pending order", overduePendingOrders.size());
        if (CollectionUtils.isEmpty(overduePendingOrders)) {
            return;
        }

        overduePendingOrders.forEach(order -> {
            order.setStatus(OrderStatus.CANCELED);
            order.setCanceledReason("Overdue pending order");
            orderRepository.update(order);
            log.info("Canceled overdue pending order {}", order.getId());
        });
    }
}
