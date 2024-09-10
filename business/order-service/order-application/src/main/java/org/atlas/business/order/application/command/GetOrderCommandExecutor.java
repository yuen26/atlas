package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.aggregator.OrderAggregator;
import org.atlas.business.order.application.contract.command.GetOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.command.contract.CommandExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetOrderCommandExecutor implements CommandExecutor<GetOrderCommand, OrderDto> {

    private final OrderRepository orderRepository;
    private final OrderAggregator orderAggregator;

    @Override
    @Transactional(readOnly = true)
    public OrderDto execute(GetOrderCommand request) throws Exception {
        Integer id = request.getId();
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));
        return orderAggregator.aggregate(order);
    }
}
