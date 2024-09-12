package org.atlas.framework.persistence.jdbc.order.supports;

import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderWithItemsExtractor implements ResultSetExtractor<List<Order>> {

    @Override
    public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Order> orders = new ArrayList<>();
        Order currentOrder = null;
        while (rs.next()) {
            int orderId = rs.getInt("order_id");
            if (currentOrder == null || currentOrder.getId() != orderId) {
                currentOrder = new Order();
                currentOrder.setId(orderId);
                currentOrder.setCustomerId(rs.getInt("customer_id"));
                currentOrder.setAmount(rs.getBigDecimal("amount"));
                currentOrder.setAddress(rs.getString("address"));
                currentOrder.setStatus(OrderStatus.valueOf(rs.getString("status")));
                currentOrder.setCreatedAt(rs.getTimestamp("created_at"));
                currentOrder.setUpdatedAt(rs.getTimestamp("updated_at"));
                orders.add(currentOrder);
            }
            if (rs.getObject("order_item_id") != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(rs.getInt("product_id"));
                orderItem.setProductPrice(rs.getBigDecimal("product_price"));
                orderItem.setQuantity(rs.getInt("quantity"));
                currentOrder.addOrderItem(orderItem);
            }
        }
        return orders;
    }
}
