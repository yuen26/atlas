package org.atlas.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.commons.context.CurrentUser;
import org.atlas.commons.context.UserContext;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.commons.utils.paging.PageDto;
import org.atlas.framework.command.executor.CommandExecutor;
import org.atlas.order.application.contract.command.ListOrderCommand;
import org.atlas.order.application.contract.model.OrderDto;
import org.atlas.order.application.service.OrderService;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.repository.FindOrderCondition;
import org.atlas.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListOrderCommandExecutor implements CommandExecutor<ListOrderCommand, PageDto<OrderDto>> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    @Transactional(readOnly = true)
    public PageDto<OrderDto> execute(ListOrderCommand command) throws Exception {
        FindOrderCondition condition = ModelMapperUtil.map(command, FindOrderCondition.class);
        CurrentUser currentUser = UserContext.getCurrentUser();
        if (currentUser.isCustomer()) {
            condition.setUserId(currentUser.getUserId());
        }
        condition.applyPaging(command.getPage(), command.getSize(), command.getSort());
        PageDto<Order> orderPage = orderRepository.find(condition);
        return toOrderDtoPage(orderPage);
    }

    private PageDto<OrderDto> toOrderDtoPage(PageDto<Order> orderPage) {
        if (orderPage.isEmpty()) {
            return PageDto.empty();
        }
        List<Order> orderList = orderPage.getRecords();
        List<OrderDto> orderDtoList = orderService.aggregate(orderList);
        return PageDto.of(orderDtoList, orderPage.getTotalCount());
    }
}
