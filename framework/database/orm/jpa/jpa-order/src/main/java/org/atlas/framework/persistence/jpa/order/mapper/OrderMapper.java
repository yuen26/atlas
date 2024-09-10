package org.atlas.framework.persistence.jpa.order.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrderItem;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {

    public static Order map(JpaOrder jpaOrder) {
        Order order = new Order();
        order.setId(jpaOrder.getId());
        order.setCustomerId(jpaOrder.getCustomerId());
        order.setAmount(jpaOrder.getAmount());
        order.setStatus(jpaOrder.getStatus());
        order.setCreatedAt(jpaOrder.getCreatedAt());
        order.setUpdatedAt(jpaOrder.getUpdatedAt());
        jpaOrder.getOrderItems().forEach(jpaOrderItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(jpaOrderItem.getProductId());
            orderItem.setProductPrice(jpaOrderItem.getProductPrice());
            orderItem.setQuantity(jpaOrderItem.getQuantity());
            order.addOrderItem(orderItem);
        });
        return order;
    }

    public static JpaOrder map(Order order, boolean ignoreOrderItems) {
        JpaOrder jpaOrder = new JpaOrder();
        jpaOrder.setId(order.getId());
        jpaOrder.setCustomerId(order.getCustomerId());
        jpaOrder.setAmount(order.getAmount());
        jpaOrder.setStatus(order.getStatus());
        if (!ignoreOrderItems) {
            order.getOrderItems().forEach(orderItem -> {
                JpaOrderItem jpaOrderItem = new JpaOrderItem();
                jpaOrderItem.setProductId(orderItem.getProductId());
                jpaOrderItem.setProductPrice(orderItem.getProductPrice());
                jpaOrderItem.setQuantity(orderItem.getQuantity());
                jpaOrder.addOrderItem(jpaOrderItem);
            });
        }
        return jpaOrder;
    }
}
