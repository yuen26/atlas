package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.UpdateOrderCommand;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.command.contract.CommandExecutor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateOrderCommandExecutor implements CommandExecutor<UpdateOrderCommand, Void> {

    private final OrderRepository orderRepository;

    @Override
    public Void execute(UpdateOrderCommand command) throws Exception {
        Order order = orderRepository.findById(command.getId())
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));
        order.setAddress(command.getAddress());
        orderRepository.update(order);
        return null;
    }
}
