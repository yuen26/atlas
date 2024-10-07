package org.atlas.framework.persistence.jdbc.auth.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.auth.domain.entity.User;
import org.atlas.auth.domain.repository.UserRepository;
import org.atlas.framework.persistence.jdbc.auth.repository.JdbcUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepositoryAdapter implements UserRepository {

    private final JdbcUserRepository jdbcUserRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return jdbcUserRepository.findByUsername(username);
    }
}
