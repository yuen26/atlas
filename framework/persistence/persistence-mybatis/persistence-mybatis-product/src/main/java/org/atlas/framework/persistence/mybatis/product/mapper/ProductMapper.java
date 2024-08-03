package org.atlas.framework.persistence.mybatis.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.FindProductCondition;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> find(@Param("condition") FindProductCondition condition);
    List<Product> findByIdIn(@Param("ids") List<Integer> ids);
    long count(@Param("query") FindProductCondition condition);
    Product findById(@Param("id") Integer id);
    int insert(Product product);
    int update(Product product);
    int increaseQuantity(@Param("id") Integer id, @Param("amount") Integer amount);
    int decreaseQuantity(@Param("id") Integer id, @Param("amount") Integer amount);
    int deleteById(@Param("id") Integer id);
}
