package org.atlas.framework.persistence.jdbc.product.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.FindProductCondition;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.framework.persistence.jdbc.core.NullSafeRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        product.setStatus(ProductStatus.valueOf(rowMapper.getString("status")));
        product.setFeatured(rowMapper.getBoolean("featured"));
        product.setCreatedAt(rowMapper.getTimestamp("created_at"));
        product.setUpdatedAt(rowMapper.getTimestamp("updated_at"));

        Category category = new Category();
        category.setId(rowMapper.getInt("category_id"));
        category.setName(rowMapper.getString("category_name"));
        product.setCategory(category);

        return product;
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Product> find(FindProductCondition condition) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String whereClause = buildWhereClause(condition, params);
        return doFind(whereClause, condition.getSort(), condition.getPage(), condition.getSize(), params);
    }

    public List<Product> findByIdIn(List<Integer> ids) {
        String sql = "SELECT * FROM product WHERE id IN (:ids)";
        SqlParameterSource params = new MapSqlParameterSource("ids", ids);
        return namedParameterJdbcTemplate.query(sql, params, rowMapper);
    }

    public long count(FindProductCondition condition) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String whereClause = buildWhereClause(condition, params);
        return doCount(whereClause, params);
    }

    public Optional<Product> findById(Integer id) {
        try {
            String sql = "SELECT * FROM product WHERE id = :id";
            SqlParameterSource params = new MapSqlParameterSource("id", id);
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper));
        } catch (DataAccessException e) {
            log.error("Occurred error while executing query", e);
            return Optional.empty();
        }
    }

    public int insert(Product product) {
        String sql = "INSERT INTO product (name, category_id, price, quantity, status, featured) " +
            "VALUES (:name, :categoryId, :price, :quantity, :status, :featured)";
        MapSqlParameterSource params = toParams(product);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});
        Number insertedId = keyHolder.getKey();
        if (insertedId != null) {
            product.setId(insertedId.intValue());
            return 1;
        } else {
            throw new RuntimeException("Failed to retrieve the inserted ID");
        }
    }

    public int update(Product product) {
        String sql = "UPDATE product" +
            " SET" +
            "   name = :name," +
            "   category_id = :categoryId" +
            "   price = :price," +
            "   quantity = :quantity," +
            "   status = :status," +
            "   featured = :featured" +
            " WHERE id = :id";
        MapSqlParameterSource params = toParams(product);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public int increaseQuantity(Integer id, Integer amount) {
        String sql = "UPDATE product" +
            " SET quantity = quantity + :amount" +
            " WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("amount", amount);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public int decreaseQuantity(Integer id, Integer amount) {
        String sql = "UPDATE product" +
            " SET quantity = quantity - :amount" +
            " WHERE id = :id" +
            "   AND quantity >= :amount";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("amount", amount);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public int deleteById(Integer id) {
        String sql = "DELETE FROM product WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    private String buildWhereClause(FindProductCondition condition, MapSqlParameterSource params) {
        StringBuilder whereClauseBuilder = new StringBuilder("WHERE 1=1 ");
        if (condition.getId() != null) {
            whereClauseBuilder.append(" AND id = :id ");
            params.addValue("id", condition.getId());
        }
        if (StringUtils.hasLength(condition.getName())) {
            whereClauseBuilder.append(" AND LOWER(name) LIKE :name ");
            params.addValue("name", "%" + condition.getName().toLowerCase() + "%");
        }
        if (condition.getCategoryId() != null) {
            whereClauseBuilder.append(" AND category_id = :categoryId ");
            params.addValue("categoryId", condition.getCategoryId());
        }
        if (condition.getMinPrice() != null) {
            whereClauseBuilder.append(" AND price >= :minPrice ");
            params.addValue("minPrice", condition.getMinPrice());
        }
        if (condition.getMaxPrice() != null) {
            whereClauseBuilder.append(" AND price <= :maxPrice ");
            params.addValue("maxPrice", condition.getMaxPrice());
        }
        if (Boolean.TRUE.equals(condition.getInStock())) {
            whereClauseBuilder.append(" AND quantity > 0 ");
        }
        if (condition.getStatus() != null) {
            whereClauseBuilder.append(" AND status = :status ");
            params.addValue("status", condition.getStatus());
        }
        if (condition.getFeatured() != null) {
            whereClauseBuilder.append(" AND featured = :featured ");
            params.addValue("featured", condition.getFeatured());
        }
        if (condition.getStartCreatedAt() != null) {
            whereClauseBuilder.append(" AND createdAt >= :startCreatedAt ");
            params.addValue("startCreatedAt", condition.getStartCreatedAt());
        }
        if (condition.getEndCreatedAt() != null) {
            whereClauseBuilder.append(" AND createdAt <= :endCreatedAt ");
            params.addValue("endCreatedAt", condition.getEndCreatedAt());
        }
        return whereClauseBuilder.toString();
    }

    private List<Product> doFind(String whereClause, String sort, Integer page, Integer size, MapSqlParameterSource params) {
        StringBuilder selectBuilder = new StringBuilder("SELECT * FROM product ");
        selectBuilder.append(whereClause);

        // Sorting
        if (StringUtils.hasLength(sort)) {
            selectBuilder.append(" ORDER BY ");
            if (sort.startsWith("-")) {
                selectBuilder.append(sort.substring(1)).append(" DESC");
            } else {
                selectBuilder.append(sort);
            }
        }

        // Paging
        if (page != null && size != null) {
            selectBuilder.append(" LIMIT :limit OFFSET :offset");
            params.addValue("limit", size)
                .addValue("offset", page * size);
        }

        String selectSql = selectBuilder.toString();
        return namedParameterJdbcTemplate.query(selectSql, params, rowMapper);
    }

    private Long doCount(String whereClause, MapSqlParameterSource params) {
        String countSql = "SELECT COUNT(id) FROM product " + whereClause;
        return namedParameterJdbcTemplate.queryForObject(countSql, params, Long.class);
    }

    private MapSqlParameterSource toParams(Product product) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", product.getId());
        params.addValue("name", product.getName());
        params.addValue("categoryId", product.getCategory().getId());
        params.addValue("price", product.getPrice());
        params.addValue("quantity", product.getQuantity());
        params.addValue("status", product.getStatus().name());
        params.addValue("featured", product.getFeatured());
        return params;
    }
}
