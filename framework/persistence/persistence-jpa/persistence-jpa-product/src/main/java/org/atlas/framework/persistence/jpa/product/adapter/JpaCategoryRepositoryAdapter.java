package org.atlas.framework.persistence.jpa.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.repository.CategoryRepository;
import org.atlas.framework.persistence.jpa.product.repository.JpaCategoryRepository;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaCategoryRepositoryAdapter implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll()
            .stream()
            .map(jpaCategory -> ModelMapperUtil.map(jpaCategory, Category.class))
            .toList();
    }
}
