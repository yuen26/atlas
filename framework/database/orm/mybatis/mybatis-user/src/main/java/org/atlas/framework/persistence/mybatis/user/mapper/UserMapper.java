package org.atlas.framework.persistence.mybatis.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.atlas.business.user.domain.entity.User;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findByIdIn(@Param("ids") List<Integer> ids);
    User findByEmail(@Param("id") String email);
    int decreaseCredit(@Param("id") Integer id, @Param("amount") BigDecimal amount);
}
