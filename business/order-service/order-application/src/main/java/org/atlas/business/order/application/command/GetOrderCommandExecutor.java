package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.GetOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.commons.context.UserContext;
import org.atlas.commons.context.UserInfo;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.command.contract.CommandExecutor;
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

        UserInfo userInfo = UserContext.getCurrentUser();
        if (userInfo.isCustomer() && !order.getCustomerId().equals(userInfo.getUserId())) {
            throw new BusinessException(AppError.PERMISSION_DENIED);
        }

        return orderService.aggregate(order);
    }
}
