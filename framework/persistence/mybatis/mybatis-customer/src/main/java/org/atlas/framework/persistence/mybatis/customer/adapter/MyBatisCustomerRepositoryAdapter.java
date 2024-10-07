package org.atlas.framework.persistence.mybatis.customer.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.customer.domain.entity.Customer;
import org.atlas.customer.domain.repository.CustomerRepository;
import org.atlas.framework.persistence.mybatis.customer.mapper.CustomerMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyBatisCustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerMapper customerMapper;

    @Override
    public List<Customer> findByUserIdIn(List<Integer> userIds) {
        return customerMapper.findByUserIdIn(userIds);
    }

    @Override
    public Optional<Customer> findByUserId(Integer userId) {
        return Optional.ofNullable(customerMapper.findByUserId(userId));
    }

    @Override
    public int decreaseCredit(Integer id, BigDecimal amount) {
        return customerMapper.decreaseCredit(id, amount);
    }
}
