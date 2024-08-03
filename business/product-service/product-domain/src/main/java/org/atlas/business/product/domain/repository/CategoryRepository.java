package org.atlas.business.product.domain.repository;

import org.atlas.business.product.domain.entity.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll();
}
