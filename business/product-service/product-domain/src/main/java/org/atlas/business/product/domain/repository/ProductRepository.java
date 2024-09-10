package org.atlas.business.product.domain.repository;

import org.atlas.business.product.domain.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findByIdIn(List<Integer> ids);
    int increaseQuantity(Integer id, Integer amount);
    int decreaseQuantity(Integer id, Integer amount);
}
