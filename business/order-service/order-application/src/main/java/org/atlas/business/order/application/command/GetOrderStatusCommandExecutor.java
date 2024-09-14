package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.GetOrderStatusCommand;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.commons.context.UserContext;
import org.atlas.commons.context.UserInfo;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.command.contract.CommandExecutor;
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

        UserInfo userInfo = UserContext.getCurrentUser();
        if (userInfo.isCustomer() && !order.getCustomerId().equals(userInfo.getUserId())) {
            throw new BusinessException(AppError.PERMISSION_DENIED);
        }

        return order.getStatus();
    }
}