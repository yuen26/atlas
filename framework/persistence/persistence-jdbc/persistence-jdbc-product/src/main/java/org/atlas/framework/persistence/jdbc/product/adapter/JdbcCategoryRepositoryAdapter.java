package org.atlas.framework.persistence.jdbc.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.repository.CategoryRepository;
import org.atlas.framework.persistence.jdbc.product.repository.JdbcCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcCategoryRepositoryAdapter implements CategoryRepository {

    private final JdbcCategoryRepository jdbcCategoryRepository;

    @Override
    public List<Category> findAll() {
        return jdbcCategoryRepository.findAll();
    }
}
