package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.GetOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.mapper.OrderMapper;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetOrderCommandExecutor implements CommandExecutor<GetOrderCommand, OrderDto> {

    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public OrderDto execute(GetOrderCommand request) throws Exception {
        Integer id = request.getId();
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));
        return OrderMapper.toOrderDto(order);
    }
}
