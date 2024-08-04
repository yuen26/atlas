package org.atlas.framework.persistence.jdbc.order.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcOrderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Order> findAll(int limit, int offset) {
        String sql = "SELECT o.id AS order_id, o.customer_id, o.amount, o.status, o.canceled_reason, o.created_at, " +
            "oi.id AS order_item_id, oi.product_id, oi.product_price, oi.quantity " +
            "FROM orders o " +
            "LEFT JOIN order_item oi ON o.id = oi.order_id " +
            "LIMIT :limit OFFSET :offset";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("limit", limit);
        parameters.addValue("offset", offset);

        return namedParameterJdbcTemplate.query(sql, parameters, new OrderWithItemsExtractor());
    }

    public long countAll() {
        String sql = "SELECT COUNT(id) FROM orders";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
    }

    public Optional<Order> findById(Integer id) {
        String sql = "SELECT o.id AS order_id, o.customer_id, o.amount, o.status, o.canceled_reason, o.created_at, " +
            "oi.id AS order_item_id, oi.product_id, oi.product_price, oi.quantity " +
            "FROM orders o " +
            "LEFT JOIN order_item oi ON o.id = oi.order_id " +
            "WHERE o.id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        List<Order> orders = namedParameterJdbcTemplate.query(sql, parameters, new OrderWithItemsExtractor());
        return CollectionUtils.isEmpty(orders) ? Optional.empty() : Optional.of(orders.get(0));
    }

    public int insert(Order order) {
        // Insert order
        String insertOrderSql = "INSERT INTO orders (customer_id, amount, status, canceled_reason) " +
            "VALUES (:customerId, :amount, :status, :canceledReason)";
        MapSqlParameterSource orderParams = toOrderParams(order);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertOrderSql, orderParams, keyHolder, new String[]{"id"});
        Number insertedOrderId = keyHolder.getKey();
        if (insertedOrderId != null) {
            order.setId(insertedOrderId.intValue());
        } else {
            throw new RuntimeException("Failed to retrieve the inserted ID");
        }

        // Insert order items
        String insertOrderItemSql = "INSERT INTO order_item (order_id, product_id, product_price, quantity) " +
            "VALUES (:orderId, :productId, :productPrice, :quantity)";
        for (OrderItem orderItem : order.getOrderItems()) {
            MapSqlParameterSource orderItemParams = toOrderItemParams(orderItem);
            namedParameterJdbcTemplate.update(insertOrderItemSql, orderItemParams);
        }

        return 1;
    }

    public int update(Order order) {
        String sql = "UPDATE orders" +
            " SET" +
            "   customer_id = :customerId," +
            "   amount = :amount," +
            "   status = :status," +
            "   canceled_reason = :canceledReason" +
            " WHERE id = :id";
        MapSqlParameterSource params = toOrderParams(order);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public List<Order> findByStatusAndCreatedBefore(OrderStatus status, Date date) {
        String sql = "SELECT o.id AS order_id, o.customer_id, o.amount, o.status, o.canceled_reason, o.created_at, " +
            "oi.id AS order_item_id, oi.product_id, oi.product_price, oi.quantity " +
            "FROM orders o " +
            "LEFT JOIN order_item oi ON o.id = oi.order_id " +
            "WHERE o.status = :status " +
            "  AND o.created_at < :date";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("status", status.name());
        parameters.addValue("date", date);

        return namedParameterJdbcTemplate.query(sql, parameters, new OrderWithItemsExtractor());
    }

    private static class OrderWithItemsExtractor implements ResultSetExtractor<List<Order>> {
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
                    currentOrder.setStatus(OrderStatus.valueOf(rs.getString("status")));
                    currentOrder.setCanceledReason(rs.getString("canceled_reason"));
                    currentOrder.setCreatedAt(rs.getTimestamp("created_at"));
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

    private MapSqlParameterSource toOrderParams(Order order) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", order.getId());
        params.addValue("customerId", order.getCustomerId());
        params.addValue("amount", order.getAmount());
        params.addValue("status", order.getStatus().name());
        params.addValue("canceledReason", order.getCanceledReason());
        return params;
    }

    private MapSqlParameterSource toOrderItemParams(OrderItem orderItem) {
        MapSqlParameterSource orderItemParams = new MapSqlParameterSource();
        orderItemParams.addValue("orderId", orderItem.getOrder().getId());
        orderItemParams.addValue("productId", orderItem.getProductId());
        orderItemParams.addValue("productPrice", orderItem.getProductPrice());
        orderItemParams.addValue("quantity", orderItem.getQuantity());
        return orderItemParams;
    }
}
