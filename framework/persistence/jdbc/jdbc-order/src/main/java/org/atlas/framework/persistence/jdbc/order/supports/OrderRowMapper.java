package org.atlas.framework.persistence.jdbc.order.supports;

import org.atlas.framework.persistence.jdbc.core.NullSafeRowMapper;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.shared.enums.OrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        NullSafeRowMapper rowMapper = new NullSafeRowMapper(rs);
        Order order = new Order();
        order.setId(rowMapper.getInt("id"));
        order.setUserId(rowMapper.getInt("user_id"));
        order.setAmount(rowMapper.getBigDecimal("amount"));
        order.setAddress(rowMapper.getString("address"));
        order.setStatus(OrderStatus.valueOf(rowMapper.getString("status")));
        order.setCreatedAt(rowMapper.getTimestamp("created_at"));
        order.setUpdatedAt(rowMapper.getTimestamp("updated_at"));
        return order;
    }
}
