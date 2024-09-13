package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.PlaceOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.commons.context.UserContext;
import org.atlas.framework.command.contract.CommandExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class PlaceOrderCommandExecutor implements CommandExecutor<PlaceOrderCommand, Integer> {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    @Transactional
    public Integer execute(PlaceOrderCommand command) {
        Order order = newOrder(command);
        OrderDto orderDto = orderService.aggregate(order);
        orderRepository.insert(order);
        orderDto.setId(order.getId());
        orderService.postCreateOrder(orderDto);
        return order.getId();
    }

    private Order newOrder(PlaceOrderCommand request) {
        Order order = new Order();
        order.setCustomerId(UserContext.getCurrentUser().getUserId());
        order.setAddress(request.getAddress());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(new Date());
        for (PlaceOrderCommand.OrderItem orderItemRequest : request.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(orderItemRequest.getProductId());
            orderItem.setQuantity(orderItemRequest.getQuantity());
            order.addOrderItem(orderItem);
        }
        return order;
    }
}
