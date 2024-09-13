package org.atlas.framework.persistence.jdbc.product.supports;

import org.atlas.business.product.domain.entity.Product;
import org.atlas.framework.persistence.jdbc.core.NullSafeRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        NullSafeRowMapper rowMapper = new NullSafeRowMapper(rs);
        Product product = new Product();
        product.setId(rowMapper.getInt("id"));
        product.setName(rowMapper.getString("name"));
        product.setPrice(rowMapper.getBigDecimal("price"));
        product.setQuantity(rowMapper.getInt("quantity"));
        product.setCreatedAt(rowMapper.getTimestamp("created_at"));
        product.setUpdatedAt(rowMapper.getTimestamp("updated_at"));
        return product;
    }
}
