package org.atlas.framework.persistence.mybatis.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.repository.CategoryRepository;
import org.atlas.framework.persistence.mybatis.product.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyBatisCategoryRepositoryAdapter implements CategoryRepository {

    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }
}
