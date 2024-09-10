package org.atlas.framework.persistence.jdbc.product.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.framework.persistence.jdbc.core.NullSafeRowMapper;
import org.springframework.jdbc.core.RowMapper;
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

    private static final RowMapper<Product> rowMapper = (rs, rowNum) -> {
        NullSafeRowMapper rowMapper = new NullSafeRowMapper(rs);
        Product product = new Product();
        product.setId(rowMapper.getInt("id"));
        product.setName(rowMapper.getString("name"));
        product.setPrice(rowMapper.getBigDecimal("price"));
        product.setQuantity(rowMapper.getInt("quantity"));
        product.setCreatedAt(rowMapper.getTimestamp("created_at"));
        product.setUpdatedAt(rowMapper.getTimestamp("updated_at"));
        return product;
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Product> findByIdIn(List<Integer> ids) {
        String sql = "SELECT * FROM product p WHERE p.id IN (:ids)";
        SqlParameterSource params = new MapSqlParameterSource("ids", ids);
        return namedParameterJdbcTemplate.query(sql, params, rowMapper);
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
