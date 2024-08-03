package org.atlas.business.user.domain.repository;

import org.atlas.business.user.domain.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
    void insert(User user);
    int decreaseCredit(Integer id, BigDecimal amount);
}
