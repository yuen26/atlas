package org.atlas.framework.persistence.jpa.product.repository;

import org.atlas.business.product.domain.repository.FindProductCondition;
import org.atlas.framework.persistence.jpa.product.entity.JpaProduct;

import java.util.List;

public interface CustomJpaProductRepository {

    List<JpaProduct> find(FindProductCondition condition);
    long count(FindProductCondition condition);
}
