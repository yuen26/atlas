package org.atlas.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.commons.context.CurrentUser;
import org.atlas.commons.context.UserContext;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.command.executor.CommandExecutor;
import org.atlas.order.application.contract.command.GetOrderStatusCommand;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.repository.OrderRepository;
import org.atlas.order.domain.shared.enums.OrderStatus;
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

        CurrentUser currentUser = UserContext.getCurrentUser();
        if (currentUser.isCustomer() && !order.getUserId().equals(currentUser.getUserId())) {
            throw new BusinessException(AppError.PERMISSION_DENIED);
        }

        return order.getStatus();
    }
}
