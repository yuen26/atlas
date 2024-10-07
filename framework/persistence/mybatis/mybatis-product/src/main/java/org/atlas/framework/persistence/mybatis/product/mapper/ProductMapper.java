package org.atlas.framework.persistence.mybatis.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.atlas.product.domain.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findByIdIn(@Param("ids") List<Integer> ids);
    int increaseQuantity(@Param("id") Integer id, @Param("amount") Integer amount);
    int decreaseQuantity(@Param("id") Integer id, @Param("amount") Integer amount);
}
