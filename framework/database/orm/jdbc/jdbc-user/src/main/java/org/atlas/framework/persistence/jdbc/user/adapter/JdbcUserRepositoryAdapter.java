package org.atlas.framework.persistence.jdbc.user.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.domain.entity.User;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.framework.persistence.jdbc.user.repository.JdbcUserRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepositoryAdapter implements UserRepository {

    private final JdbcUserRepository jdbcUserRepository;

    @Override
    public List<User> findByIdIn(List<Integer> ids) {
        return jdbcUserRepository.findByIdIn(ids);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcUserRepository.findByEmail(email);
    }

    @Override
    public int decreaseCredit(Integer id, BigDecimal amount) {
        return jdbcUserRepository.decreaseCredit(id, amount);
    }
}
