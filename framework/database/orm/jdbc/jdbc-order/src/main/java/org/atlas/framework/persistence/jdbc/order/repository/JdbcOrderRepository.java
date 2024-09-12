package org.atlas.framework.persistence.jdbc.order.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.repository.FindOrderCondition;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

    public List<Order> find(FindOrderCondition condition) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String whereClause = buildWhereClause(condition, params);
        return doFind(whereClause, condition, params);
    }

    public long count(FindOrderCondition condition) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String whereClause = buildWhereClause(condition, params);
        return doCount(whereClause, params);
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
        String insertOrderSql = "INSERT INTO orders (customer_id, amount, address, status) " +
            "VALUES (:customerId, :amount, :address, :status)";
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
        StringBuilder sqlBuilder = new StringBuilder("UPDATE orders o SET ");
        if (order.getStatus() != null) {
            sqlBuilder.append("o.status = :status ");
        }
        if (order.getAddress() != null) {
            sqlBuilder.append("o.address = :address ");
        }
        sqlBuilder.append("WHERE o.id = :id");
        String sql = sqlBuilder.toString();

        MapSqlParameterSource params = toOrderParams(order);

        return namedParameterJdbcTemplate.update(sql, params);
    }

    public int deleteById(Integer id) {
        String sql = "DELETE FROM orders o WHERE o.id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public int softDeleteByStatusAndCreatedBefore(OrderStatus status, Date date) {
        String sql = "UPDATE orders o SET o.deleted = true " +
            "WHERE o.status = :status " +
            "  AND o.created_at < :date";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("status", status.name());
        parameters.addValue("date", date);

        return namedParameterJdbcTemplate.update(sql, parameters);
    }

    private String buildWhereClause(FindOrderCondition condition, MapSqlParameterSource params) {
        StringBuilder whereClauseBuilder = new StringBuilder("WHERE 1=1 ");
        if (condition.getId() != null) {
            whereClauseBuilder.append("AND o.id = :id ");
            params.addValue("id", condition.getId());
        }
        if (condition.getCustomerId() != null) {
            whereClauseBuilder.append("AND o.customer_id = :customerId ");
            params.addValue("customerId", condition.getCustomerId());
        }
        if (condition.getMinAmount() != null) {
            whereClauseBuilder.append("AND o.amount >= :minAmount ");
            params.addValue("minAmount", condition.getMinAmount());
        }
        if (condition.getMaxAmount() != null) {
            whereClauseBuilder.append("AND o.amount <= :maxAmount ");
            params.addValue("maxAmount", condition.getMaxAmount());
        }
        if (StringUtils.hasLength(condition.getAddress())) {
            whereClauseBuilder.append("AND LOWER(o.address) LIKE :address ");
            params.addValue("address", "%" + condition.getAddress().toLowerCase() + "%");
        }
        if (condition.getStatus() != null) {
            whereClauseBuilder.append("AND o.status = :status ");
            params.addValue("status", condition.getStatus());
        }
        if (condition.getDeleted() != null) {
            whereClauseBuilder.append("AND o.deleted = :deleted ");
            params.addValue("deleted", condition.getDeleted());
        }
        if (condition.getStartCreatedAt() != null) {
            whereClauseBuilder.append("AND o.createdAt >= :startCreatedAt ");
            params.addValue("startCreatedAt", condition.getStartCreatedAt());
        }
        if (condition.getEndCreatedAt() != null) {
            whereClauseBuilder.append("AND o.createdAt <= :endCreatedAt ");
            params.addValue("endCreatedAt", condition.getEndCreatedAt());
        }
        return whereClauseBuilder.toString();
    }

    private List<Order> doFind(String whereClause, FindOrderCondition condition, MapSqlParameterSource params) {
        StringBuilder sqlBuilder = new StringBuilder(
            "SELECT o.id AS order_id, o.customer_id, o.amount, o.address, o.status, o.created_at, " +
            "oi.id AS order_item_id, oi.product_id, oi.product_price, oi.quantity " +
            "FROM orders o " +
            "LEFT JOIN order_item oi ON o.id = oi.order_id ");

        sqlBuilder.append(whereClause);

        // Sorting
        if (StringUtils.hasLength(condition.getSortBy())) {
            sqlBuilder.append(" ORDER BY :sortBy :sortOrder");
            params.addValue("sortBy", condition.getSortBy())
                .addValue("sortOrder", condition.getSortOrder());
        }

        // Paging
        if (condition.getLimit() != null && condition.getOffset() != null) {
            sqlBuilder.append(" LIMIT :limit OFFSET :offset");
            params.addValue("limit", condition.getLimit())
                .addValue("offset", condition.getOffset());
        }

        String sql = sqlBuilder.toString();
        return namedParameterJdbcTemplate.query(sql, params, new OrderWithItemsExtractor());
    }

    private Long doCount(String whereClause, MapSqlParameterSource params) {
        String sql = "SELECT COUNT(o.id) FROM orders o " + whereClause;
        return namedParameterJdbcTemplate.queryForObject(sql, params, Long.class);
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
        params.addValue("address", order.getAddress());
        params.addValue("status", order.getStatus().name());
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
