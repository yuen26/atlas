package org.atlas.customer.domain.repository;

import org.atlas.customer.domain.entity.Customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findByUserIdIn(List<Integer> userIds);
    Optional<Customer> findByUserId(Integer userId);
    int decreaseCredit(Integer userId, BigDecimal amount);
}
