package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.GetOrderStatusCommand;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetOrderStatusCommandExecutor implements CommandExecutor<GetOrderStatusCommand, OrderStatus> {

    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public OrderStatus execute(GetOrderStatusCommand command) throws Exception {
        Order order = orderRepository.findById(command.getId())
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));
        return order.getStatus();
    }
}
