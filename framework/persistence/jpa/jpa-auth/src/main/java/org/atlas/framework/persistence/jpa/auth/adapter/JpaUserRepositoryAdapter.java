package org.atlas.framework.persistence.jpa.auth.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.auth.domain.entity.User;
import org.atlas.auth.domain.repository.UserRepository;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.framework.persistence.jpa.auth.repository.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username)
            .map(jpaUser -> ModelMapperUtil.map(jpaUser, User.class));
    }
}
