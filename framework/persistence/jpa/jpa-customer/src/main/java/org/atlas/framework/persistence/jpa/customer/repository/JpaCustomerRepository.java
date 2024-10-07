package org.atlas.framework.persistence.jpa.customer.repository;

import org.atlas.framework.persistence.jpa.core.repository.JpaBaseRepository;
import org.atlas.framework.persistence.jpa.customer.entity.JpaCustomer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface JpaCustomerRepository extends JpaBaseRepository<JpaCustomer, Integer> {

    @Modifying
    @Query(
        "update JpaCustomer u " +
            "set u.credit = u.credit - :amount " +
            "where u.userId = :userId and u.credit >= :amount"
    )
    int decreaseCredit(@Param("userId") Integer userId,
                       @Param("amount") BigDecimal amount);
}
