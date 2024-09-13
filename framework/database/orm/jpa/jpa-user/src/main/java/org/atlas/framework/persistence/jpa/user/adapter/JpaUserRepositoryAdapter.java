package org.atlas.framework.persistence.jpa.user.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.domain.entity.User;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.framework.persistence.jpa.user.repository.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public List<User> findByIdIn(List<Integer> ids) {
        return jpaUserRepository.findAllById(ids)
            .stream()
            .map(jpaUser -> ModelMapperUtil.map(jpaUser, User.class))
            .toList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
            .map(jpaUser -> ModelMapperUtil.map(jpaUser, User.class));
    }

    @Override
    public int decreaseCredit(Integer id, BigDecimal amount) {
        return jpaUserRepository.decreaseCredit(id, amount);
    }
}
