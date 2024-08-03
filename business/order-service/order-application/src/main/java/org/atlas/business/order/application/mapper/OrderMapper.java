package org.atlas.business.order.application.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.contract.model.OrderItemDto;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.framework.event.contract.order.model.OrderData;
import org.atlas.framework.event.contract.order.model.OrderItemData;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {

    public static OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setAmount(order.getAmount());
        orderDto.setStatus(order.getStatus());
        orderDto.setCreatedAt(order.getCreatedAt());
        order.getOrderItems().forEach(orderItem -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setProductId(orderItem.getProductId());
            orderItemDto.setProductPrice(orderItem.getProductPrice());
            orderItemDto.setQuantity(orderItem.getQuantity());
            orderDto.addOrderItem(orderItemDto);
        });
        return orderDto;
    }

    public static OrderData toOrderData(Order order) {
        OrderData orderData = new OrderData();
        orderData.setOrderId(order.getId());
        orderData.setCustomerId(order.getCustomerId());
        orderData.setAmount(order.getAmount());
        orderData.setCreatedAt(order.getCreatedAt());
        order.getOrderItems().forEach(orderItemEntity -> {
            OrderItemData orderItemData = new OrderItemData();
            orderItemData.setProductId(orderItemEntity.getProductId());
            orderItemData.setProductPrice(orderItemEntity.getProductPrice());
            orderItemData.setQuantity(orderItemEntity.getQuantity());
            orderData.addOrderItem(orderItemData);
        });
        return orderData;
    }
}
