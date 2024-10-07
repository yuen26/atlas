package org.atlas.framework.persistence.jdbc.customer.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.customer.domain.entity.Customer;
import org.atlas.customer.domain.repository.CustomerRepository;
import org.atlas.framework.persistence.jdbc.customer.repository.JdbcCustomerRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcCustomerRepositoryAdapter implements CustomerRepository {

    private final JdbcCustomerRepository jdbcCustomerRepository;

    @Override
    public List<Customer> findByUserIdIn(List<Integer> userIds) {
        return jdbcCustomerRepository.findByUserIdIn(userIds);
    }

    @Override
    public Optional<Customer> findByUserId(Integer userId) {
        return jdbcCustomerRepository.findByUserId(userId);
    }

    @Override
    public int decreaseCredit(Integer userId, BigDecimal amount) {
        return jdbcCustomerRepository.decreaseCredit(userId, amount);
    }
}
