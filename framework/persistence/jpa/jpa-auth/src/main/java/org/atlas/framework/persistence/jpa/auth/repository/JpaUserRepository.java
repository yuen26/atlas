package org.atlas.framework.persistence.jpa.auth.repository;

import org.atlas.framework.persistence.jpa.auth.entity.JpaUser;
import org.atlas.framework.persistence.jpa.core.repository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaBaseRepository<JpaUser, Integer> {

    Optional<JpaUser> findByUsername(String username);
}
