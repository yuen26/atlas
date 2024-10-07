package org.atlas.auth.domain.repository;

import org.atlas.auth.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String email);
}
