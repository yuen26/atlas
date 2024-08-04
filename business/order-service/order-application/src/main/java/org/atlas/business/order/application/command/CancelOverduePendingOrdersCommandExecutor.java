package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.atlas.business.order.application.contract.command.CancelOverduePendingOrdersCommand;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.command.contract.CommandExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CancelOverduePendingOrdersCommandExecutor implements CommandExecutor<CancelOverduePendingOrdersCommand, Void> {

    private final OrderRepository orderRepository;

    private static final int TIMEOUT_IN_MINUTES = 15;

    @Override
    @Transactional
    public Void execute(CancelOverduePendingOrdersCommand command) throws Exception {
        Date now = new Date();
        Date dueDate = DateUtils.addMinutes(now, -1 * TIMEOUT_IN_MINUTES);
        List<Order> overduePendingOrders = orderRepository.findByStatusAndCreatedBefore(OrderStatus.PENDING, dueDate);
        if (CollectionUtils.isEmpty(overduePendingOrders)) {
            log.info("Not found any overdue pending order");
            return null;
        }

        overduePendingOrders.forEach(order -> {
            order.setStatus(OrderStatus.CANCELED);
            order.setCanceledReason("Overdue pending order");
            orderRepository.update(order);
            log.info("Canceled overdue pending order {}", order.getId());
        });

        return null;
    }
}
