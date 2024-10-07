package org.atlas.framework.persistence.jdbc.order.supports;

import org.atlas.framework.persistence.jdbc.core.NullSafeRowMapper;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.entity.OrderItem;
import org.atlas.order.domain.shared.enums.OrderStatus;
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
            NullSafeRowMapper rowMapper = new NullSafeRowMapper(rs);
            int orderId = rs.getInt("order_id");
            if (currentOrder == null || currentOrder.getId() != orderId) {
                currentOrder = new Order();
                currentOrder.setId(orderId);
                currentOrder.setUserId(rowMapper.getInt("user_id"));
                currentOrder.setAmount(rowMapper.getBigDecimal("amount"));
                currentOrder.setAddress(rowMapper.getString("address"));
                currentOrder.setStatus(OrderStatus.valueOf(rowMapper.getString("status")));
                currentOrder.setCreatedAt(rowMapper.getTimestamp("created_at"));
                currentOrder.setUpdatedAt(rowMapper.getTimestamp("updated_at"));
                orders.add(currentOrder);
            }
            if (rs.getObject("order_item_id") != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(rowMapper.getInt("product_id"));
                orderItem.setProductPrice(rowMapper.getBigDecimal("product_price"));
                orderItem.setQuantity(rowMapper.getInt("quantity"));
                currentOrder.addOrderItem(orderItem);
            }
        }
        return orders;
    }
}
