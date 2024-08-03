package org.atlas.framework.persistence.jpa.product.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.atlas.business.product.domain.repository.FindProductCondition;
import org.atlas.framework.persistence.jpa.core.specification.QueryFilter;
import org.atlas.framework.persistence.jpa.core.specification.QueryOperation;
import org.atlas.framework.persistence.jpa.core.specification.QuerySpecification;
import org.atlas.framework.persistence.jpa.product.entity.JpaProduct;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CustomJpaProductRepositoryUsingCriteria implements CustomJpaProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<JpaProduct> find(FindProductCondition condition) {
        Specification<JpaProduct> spec = buildSpec(condition);
        return find(spec, condition.getSort(), condition.getPage(), condition.getSize());
    }

    @Override
    public long count(FindProductCondition condition) {
        Specification<JpaProduct> spec = buildSpec(condition);
        return count(spec);
    }

    private Specification<JpaProduct> buildSpec(FindProductCondition condition) {
        QuerySpecification<JpaProduct> spec = new QuerySpecification<>();
        if (condition.getId() != null) {
            spec.addFilter(QueryFilter.of("id", condition.getId(), QueryOperation.EQUAL));
        }
        if (StringUtils.hasLength(condition.getName())) {
            spec.addFilter(QueryFilter.of("name", condition.getName(), QueryOperation.LIKE));
        }
        if (condition.getCategoryId() != null) {
            spec.addFilter(QueryFilter.of("category.id", condition.getCategoryId(), QueryOperation.EQUAL));
        }
        if (condition.getMinPrice() != null) {
            spec.addFilter(QueryFilter.of("price", condition.getMinPrice(), QueryOperation.GREATER_THAN_EQUAL));
        }
        if (condition.getMaxPrice() != null) {
            spec.addFilter(QueryFilter.of("price", condition.getMaxPrice(), QueryOperation.LESS_THAN_EQUAL));
        }
        if (Boolean.TRUE.equals(condition.getInStock())) {
            spec.addFilter(QueryFilter.of("quantity", 0, QueryOperation.GREATER_THAN));
        }
        if (condition.getStatus() != null) {
            spec.addFilter(QueryFilter.of("status", condition.getStatus(), QueryOperation.EQUAL));
        }
        if (condition.getFeatured() != null) {
            spec.addFilter(QueryFilter.of("featured", condition.getFeatured(), QueryOperation.EQUAL));
        }
        if (condition.getStartCreatedAt() != null) {
            spec.addFilter(QueryFilter.of("createdAt", condition.getStartCreatedAt(), QueryOperation.GREATER_THAN_EQUAL));
        }
        if (condition.getEndCreatedAt() != null) {
            spec.addFilter(QueryFilter.of("createdAt", condition.getEndCreatedAt(), QueryOperation.LESS_THAN_EQUAL));
        }
        return spec;
    }

    private Long count(Specification<JpaProduct> spec) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<JpaProduct> root = query.from(JpaProduct.class);
        query.select(criteriaBuilder.count(root.get("id")));
        Predicate predicate = spec.toPredicate(root, query, criteriaBuilder);
        query.where(predicate);
        return entityManager.createQuery(query).getSingleResult();
    }

    private List<JpaProduct> find(Specification<JpaProduct> spec, String sort, Integer pageNumber, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaProduct> query = criteriaBuilder.createQuery(JpaProduct.class);
        Root<JpaProduct> root = query.from(JpaProduct.class);

        // Use join fetch for the category entity
        root.fetch("category", JoinType.LEFT);

        Predicate predicate = spec.toPredicate(root, query, criteriaBuilder);
        query.where(predicate);

        // Sorting
        if (StringUtils.hasLength(sort)) {
            if (sort.startsWith("-")) {
                query.orderBy(criteriaBuilder.desc(root.get(sort.substring(1))));
            } else {
                query.orderBy(criteriaBuilder.asc(root.get(sort)));
            }
        }

        TypedQuery<JpaProduct> typedQuery = entityManager.createQuery(query);

        // Paging
        if (pageNumber != null && pageSize != null) {
            typedQuery.setFirstResult(pageNumber * pageSize);
            typedQuery.setMaxResults(pageSize);
        }

        return typedQuery.getResultList();
    }
}
