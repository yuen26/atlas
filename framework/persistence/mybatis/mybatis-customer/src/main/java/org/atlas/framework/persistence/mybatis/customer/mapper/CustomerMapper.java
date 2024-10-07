package org.atlas.framework.persistence.mybatis.customer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.atlas.customer.domain.entity.Customer;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CustomerMapper {

    List<Customer> findByUserIdIn(@Param("userIds") List<Integer> userIds);
    Customer findByUserId(@Param("userId") Integer userId);
    int decreaseCredit(@Param("userId") Integer userId, @Param("amount") BigDecimal amount);
}
