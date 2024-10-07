package org.atlas.framework.persistence.mybatis.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.atlas.auth.domain.entity.User;

@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
