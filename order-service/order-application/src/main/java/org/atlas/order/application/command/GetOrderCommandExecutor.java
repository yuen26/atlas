package org.atlas.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.commons.context.CurrentUser;
import org.atlas.commons.context.UserContext;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.command.executor.CommandExecutor;
import org.atlas.order.application.contract.command.GetOrderCommand;
import org.atlas.order.application.contract.model.OrderDto;
import org.atlas.order.application.service.OrderService;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetOrderCommandExecutor implements CommandExecutor<GetOrderCommand, OrderDto> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    @Transactional(readOnly = true)
    public OrderDto execute(GetOrderCommand command) throws Exception {
        Order order = orderRepository.findById(command.getId())
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));

        CurrentUser currentUser = UserContext.getCurrentUser();
        if (currentUser.isCustomer() && !order.getUserId().equals(currentUser.getUserId())) {
            throw new BusinessException(AppError.PERMISSION_DENIED);
        }

        return orderService.aggregate(order);
    }
}
