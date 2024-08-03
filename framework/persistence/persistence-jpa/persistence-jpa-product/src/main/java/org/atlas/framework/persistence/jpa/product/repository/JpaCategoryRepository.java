package org.atlas.framework.persistence.jpa.product.repository;

import org.atlas.framework.persistence.jpa.core.repository.JpaBaseRepository;
import org.atlas.framework.persistence.jpa.product.entity.JpaCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaBaseRepository<JpaCategory, Integer> {
}
