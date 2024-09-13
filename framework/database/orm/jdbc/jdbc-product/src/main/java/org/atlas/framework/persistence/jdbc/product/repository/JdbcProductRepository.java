package org.atlas.framework.persistence.jdbc.product.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.framework.persistence.jdbc.product.supports.ProductRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcProductRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Product> findByIdIn(List<Integer> ids) {
        String sql = "SELECT * FROM product p WHERE p.id IN (:ids)";
        SqlParameterSource params = new MapSqlParameterSource("ids", ids);
        return namedParameterJdbcTemplate.query(sql, params, new ProductRowMapper());
    }

    public int increaseQuantity(Integer id, Integer amount) {
        String sql = "UPDATE product p " +
            "SET p.quantity = p.quantity + :amount " +
            "WHERE p.id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("amount", amount);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public int decreaseQuantity(Integer id, Integer amount) {
        String sql = "UPDATE product p " +
            "SET p.quantity = p.quantity - :amount " +
            "WHERE p.id = :id " +
            "  AND p.quantity >= :amount";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("amount", amount);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
