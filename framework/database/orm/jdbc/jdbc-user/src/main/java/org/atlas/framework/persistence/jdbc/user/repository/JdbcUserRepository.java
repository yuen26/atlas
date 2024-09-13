package org.atlas.framework.persistence.jdbc.user.repository;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.domain.entity.User;
import org.atlas.framework.persistence.jdbc.user.supports.UserRowMapper;
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
public class JdbcUserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> findByIdIn(List<Integer> ids) {
        String sql = "SELECT * FROM users u WHERE u.id IN (:ids)";
        SqlParameterSource params = new MapSqlParameterSource("ids", ids);
        return namedParameterJdbcTemplate.query(sql, params, new UserRowMapper());
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users u WHERE u.email = :email";
        SqlParameterSource params = new MapSqlParameterSource("email", email);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int decreaseCredit(Integer id, BigDecimal amount) {
        String sql = "UPDATE users u " +
            "SET u.credit = u.credit - :amount " +
            "WHERE u.id = :id" +
            "  AND u.credit >= :amount";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("amount", amount);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
