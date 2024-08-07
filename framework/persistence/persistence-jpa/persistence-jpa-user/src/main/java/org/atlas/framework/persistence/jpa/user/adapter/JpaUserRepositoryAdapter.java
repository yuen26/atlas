package org.atlas.framework.persistence.jpa.user.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.domain.entity.User;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.framework.persistence.jpa.user.entity.JpaUser;
import org.atlas.framework.persistence.jpa.user.repository.JpaUserRepository;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findById(Integer id) {
        return jpaUserRepository.findById(id)
            .map(jpaUser -> ModelMapperUtil.map(jpaUser, User.class));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
            .map(jpaUser -> ModelMapperUtil.map(jpaUser, User.class));
    }

    @Override
    public void insert(User user) {
        JpaUser jpaUser = ModelMapperUtil.map(user, JpaUser.class);
        jpaUserRepository.insert(jpaUser);
        user.setId(jpaUser.getId());
    }

    @Override
    public int decreaseCredit(Integer id, BigDecimal amount) {
        return jpaUserRepository.decreaseCredit(id, amount);
    }
}
