package org.atlas.framework.persistence.jdbc.customer.supports;

import org.atlas.customer.domain.entity.Customer;
import org.atlas.framework.persistence.jdbc.core.NullSafeRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        NullSafeRowMapper rowMapper = new NullSafeRowMapper(rs);
        Customer customer = new Customer();
        customer.setUserId(rowMapper.getInt("user_id"));
        customer.setFirstName(rowMapper.getString("first_name"));
        customer.setLastName(rowMapper.getString("last_name"));
        customer.setEmail(rowMapper.getString("email"));
        customer.setPhoneNumber(rowMapper.getString("phone_number"));
        customer.setCredit(rowMapper.getBigDecimal("credit"));
        customer.setCreatedAt(rowMapper.getTimestamp("created_at"));
        customer.setUpdatedAt(rowMapper.getTimestamp("updated_at"));
        return customer;
    }
}
