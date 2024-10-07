package org.atlas.framework.persistence.jdbc.customer.repository;

import lombok.RequiredArgsConstructor;
import org.atlas.customer.domain.entity.Customer;
import org.atlas.framework.persistence.jdbc.customer.supports.CustomerRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcCustomerRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Customer> findByUserIdIn(List<Integer> userIds) {
        String sql = "SELECT * FROM customer c WHERE c.user_id IN (:userIds)";
        SqlParameterSource params = new MapSqlParameterSource("userIds", userIds);
        return namedParameterJdbcTemplate.query(sql, params, new CustomerRowMapper());
    }

    public Optional<Customer> findByUserId(Integer userId) {
        String sql = "SELECT * FROM customer c WHERE c.user_id = :userId";
        SqlParameterSource params = new MapSqlParameterSource("userId", userId);
        try {
            Customer customer = namedParameterJdbcTemplate.queryForObject(sql, params, new CustomerRowMapper());
            return Optional.ofNullable(customer);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int decreaseCredit(Integer userId, BigDecimal amount) {
        String sql = "UPDATE customer c " +
            "SET c.credit = c.credit - :amount " +
            "WHERE c.userId = :userId" +
            "  AND c.credit >= :amount";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("amount", amount);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
