package org.atlas.framework.persistence.jdbc.auth.supports;

import org.atlas.auth.domain.entity.User;
import org.atlas.auth.domain.shared.enums.Role;
import org.atlas.framework.persistence.jdbc.core.NullSafeRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        NullSafeRowMapper rowMapper = new NullSafeRowMapper(rs);
        User user = new User();
        user.setId(rowMapper.getInt("id"));
        user.setUsername(rowMapper.getString("username"));
        user.setPassword(rowMapper.getString("password"));
        user.setRole(Role.valueOf(rowMapper.getString("role")));
        user.setCreatedAt(rowMapper.getTimestamp("created_at"));
        user.setUpdatedAt(rowMapper.getTimestamp("updated_at"));
        return user;
    }
}
