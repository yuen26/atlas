package org.atlas.framework.persistence.mybatis.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.atlas.business.product.domain.entity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> findAll();
}
