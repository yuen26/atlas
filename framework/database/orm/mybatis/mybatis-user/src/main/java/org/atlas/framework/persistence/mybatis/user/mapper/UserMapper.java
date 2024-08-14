package org.atlas.framework.persistence.mybatis.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.atlas.business.user.domain.entity.User;

import java.math.BigDecimal;

@Mapper
public interface UserMapper {

    User findById(@Param("id") Integer id);
    User findByEmail(@Param("id") String email);
    int insert(User user);
    int decreaseCredit(@Param("id") Integer id, @Param("amount") BigDecimal amount);
}
