package org.atlas.framework.persistence.jpa.user.repository;

import org.atlas.framework.persistence.jpa.core.repository.JpaBaseRepository;
import org.atlas.framework.persistence.jpa.user.entity.JpaUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaBaseRepository<JpaUser, Integer> {

    Optional<JpaUser> findByEmail(String email);

    @Modifying
    @Query(
        "update JpaUser u " +
            "set u.credit = u.credit - :amount " +
            "where u.id = :id and u.credit >= :amount"
    )
    int decreaseCredit(@Param("id") Integer id,
                       @Param("amount") BigDecimal amount);
}
