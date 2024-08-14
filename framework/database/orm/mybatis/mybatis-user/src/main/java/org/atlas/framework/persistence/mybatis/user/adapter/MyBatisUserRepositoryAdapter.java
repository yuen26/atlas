package org.atlas.framework.persistence.mybatis.user.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.domain.entity.User;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.framework.persistence.mybatis.user.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyBatisUserRepositoryAdapter implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(userMapper.findById(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userMapper.findByEmail(email));
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public int decreaseCredit(Integer id, BigDecimal amount) {
        return userMapper.decreaseCredit(id, amount);
    }
}
