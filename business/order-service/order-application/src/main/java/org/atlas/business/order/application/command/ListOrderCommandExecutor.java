package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.ListOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.mapper.OrderMapper;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ListOrderCommandExecutor implements CommandExecutor<ListOrderCommand, PageDto<OrderDto>> {

    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public PageDto<OrderDto> execute(ListOrderCommand command) throws Exception {
        PageDto<Order> orderPage = orderRepository.find(command.getPage() - 1, command.getSize());
        return orderPage.map(OrderMapper::toOrderDto);
    }
}
