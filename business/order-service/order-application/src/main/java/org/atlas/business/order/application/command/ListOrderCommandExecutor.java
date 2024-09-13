package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.ListOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.FindOrderCondition;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.commons.context.UserContext;
import org.atlas.commons.context.UserInfo;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.commons.utils.paging.PageDto;
import org.atlas.framework.command.contract.CommandExecutor;
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
        UserInfo userInfo = UserContext.getCurrentUser();
        if (userInfo.isCustomer()) {
            condition.setCustomerId(userInfo.getUserId());
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
