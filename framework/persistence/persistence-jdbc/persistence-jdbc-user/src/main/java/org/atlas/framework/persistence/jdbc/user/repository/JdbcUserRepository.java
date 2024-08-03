package org.atlas.framework.persistence.jdbc.user.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.user.domain.entity.User;
import org.atlas.business.user.domain.shared.enums.Role;
import org.atlas.framework.persistence.jdbc.core.NullSafeRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcUserRepository {

    private static final RowMapper<User> rowMapper = (rs, rowNum) -> {
        NullSafeRowMapper rowMapper = new NullSafeRowMapper(rs);
        User user = new User();
        user.setId(rowMapper.getInt("id"));
        user.setUsername(rowMapper.getString("username"));
        user.setEmail(rowMapper.getString("email"));
        user.setPassword(rowMapper.getString("password"));
        user.setRole(Role.valueOf(rowMapper.getString("role")));
        user.setCredit(rowMapper.getBigDecimal("credit"));
        user.setCreatedAt(rowMapper.getTimestamp("created_at"));
        user.setUpdatedAt(rowMapper.getTimestamp("updated_at"));
        return user;
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<User> findById(Integer id) {
        try {
            String sql = "SELECT * FROM users WHERE id = :id";
            SqlParameterSource params = new MapSqlParameterSource("id", id);
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper));
        } catch (DataAccessException e) {
            log.error("Occurred error while executing query", e);
            return Optional.empty();
        }
    }

    public Optional<User> findByEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = :email";
            SqlParameterSource params = new MapSqlParameterSource("email", email);
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper));
        } catch (DataAccessException e) {
            log.error("Occurred error while executing query", e);
            return Optional.empty();
        }
    }

    public int insert(User user) {
        String sql = "INSERT INTO users (username, email, password, role, credit) " +
            "VALUES (:username, :email, :password, :role, :credit)";
        MapSqlParameterSource params = toParams(user);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});
        Number insertedId = keyHolder.getKey();
        if (insertedId != null) {
            user.setId(insertedId.intValue());
            return 1;
        } else {
            throw new RuntimeException("Failed to retrieve the inserted ID");
        }
    }

    public int decreaseCredit(Integer id, BigDecimal amount) {
        String sql = "UPDATE users" +
            " SET credit = credit - :amount" +
            " WHERE id = :id" +
            "   AND credit >= :amount";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("amount", amount);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    private MapSqlParameterSource toParams(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());
        params.addValue("username", user.getUsername());
        params.addValue("email", user.getEmail());
        params.addValue("password", user.getPassword());
        params.addValue("role", user.getRole().name());
        params.addValue("credit", user.getCredit());
        return params;
    }
}
