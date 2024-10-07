package org.atlas.framework.persistence.jdbc.auth.repository;

import lombok.RequiredArgsConstructor;
import org.atlas.auth.domain.entity.User;
import org.atlas.framework.persistence.jdbc.auth.supports.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users u WHERE u.username = :username";
        SqlParameterSource params = new MapSqlParameterSource("username", username);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
