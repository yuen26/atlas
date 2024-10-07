package org.atlas.framework.persistence.jpa.customer.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.customer.domain.entity.Customer;
import org.atlas.customer.domain.repository.CustomerRepository;
import org.atlas.framework.persistence.jpa.customer.repository.JpaCustomerRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaCustomerRepositoryAdapter implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;

    @Override
    public List<Customer> findByUserIdIn(List<Integer> userIds) {
        return jpaCustomerRepository.findAllById(userIds)
            .stream()
            .map(jpaCustomer -> ModelMapperUtil.map(jpaCustomer, Customer.class))
            .toList();
    }

    @Override
    public Optional<Customer> findByUserId(Integer userId) {
        return jpaCustomerRepository.findById(userId)
            .map(jpaCustomer -> ModelMapperUtil.map(jpaCustomer, Customer.class));
    }

    @Override
    public int decreaseCredit(Integer userId, BigDecimal amount) {
        return jpaCustomerRepository.decreaseCredit(userId, amount);
    }
}
