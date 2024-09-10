package org.atlas.business.user.domain.repository;

import org.atlas.business.user.domain.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findByIdIn(List<Integer> ids);
    Optional<User> findByEmail(String email);
    int decreaseCredit(Integer id, BigDecimal amount);
}
