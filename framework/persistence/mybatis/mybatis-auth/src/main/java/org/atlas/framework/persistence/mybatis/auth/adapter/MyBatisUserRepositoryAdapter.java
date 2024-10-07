package org.atlas.framework.persistence.mybatis.auth.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.auth.domain.entity.User;
import org.atlas.auth.domain.repository.UserRepository;
import org.atlas.framework.persistence.mybatis.auth.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyBatisUserRepositoryAdapter implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userMapper.findByUsername(username));
    }
}
