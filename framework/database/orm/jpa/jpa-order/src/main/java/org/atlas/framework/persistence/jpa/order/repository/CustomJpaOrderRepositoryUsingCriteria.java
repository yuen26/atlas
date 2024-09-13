package org.atlas.framework.persistence.jpa.order.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.atlas.business.order.domain.repository.FindOrderCondition;
import org.atlas.framework.persistence.jpa.core.specification.QueryFilter;
import org.atlas.framework.persistence.jpa.core.specification.QueryOperation;
import org.atlas.framework.persistence.jpa.core.specification.QuerySpecification;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CustomJpaOrderRepositoryUsingCriteria implements CustomJpaOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<JpaOrder> find(FindOrderCondition condition) {
        Specification<JpaOrder> spec = buildSpec(condition);
        return find(spec, condition);
    }

    @Override
    public long count(FindOrderCondition condition) {
        Specification<JpaOrder> spec = buildSpec(condition);
        return count(spec);
    }

    private Specification<JpaOrder> buildSpec(FindOrderCondition condition) {
        QuerySpecification<JpaOrder> spec = new QuerySpecification<>();
        if (condition.getId() != null) {
            spec.addFilter(QueryFilter.of("id", condition.getId(), QueryOperation.EQUAL));
        }
        if (condition.getCustomerId() != null) {
            spec.addFilter(QueryFilter.of("customerId", condition.getCustomerId(), QueryOperation.EQUAL));
        }
        if (condition.getMinAmount() != null) {
            spec.addFilter(QueryFilter.of("amount", condition.getMinAmount(), QueryOperation.GREATER_THAN_EQUAL));
        }
        if (condition.getMaxAmount() != null) {
            spec.addFilter(QueryFilter.of("amount", condition.getMaxAmount(), QueryOperation.LESS_THAN_EQUAL));
        }
        if (StringUtils.hasLength(condition.getAddress())) {
            spec.addFilter(QueryFilter.of("address", condition.getAddress(), QueryOperation.LIKE));
        }
        if (condition.getStatus() != null) {
            spec.addFilter(QueryFilter.of("status", condition.getStatus(), QueryOperation.EQUAL));
        }
        if (condition.getStartCreatedAt() != null) {
            spec.addFilter(QueryFilter.of("createdAt", condition.getStartCreatedAt(), QueryOperation.GREATER_THAN_EQUAL));
        }
        if (condition.getEndCreatedAt() != null) {
            spec.addFilter(QueryFilter.of("createdAt", condition.getEndCreatedAt(), QueryOperation.LESS_THAN_EQUAL));
        }
        return spec;
    }

    private Long count(Specification<JpaOrder> spec) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<JpaOrder> root = query.from(JpaOrder.class);
        query.select(criteriaBuilder.count(root.get("orderId")));
        Predicate predicate = spec.toPredicate(root, query, criteriaBuilder);
        query.where(predicate);
        return entityManager.createQuery(query).getSingleResult();
    }

    private List<JpaOrder> find(Specification<JpaOrder> spec, FindOrderCondition condition) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaOrder> query = criteriaBuilder.createQuery(JpaOrder.class);
        Root<JpaOrder> root = query.from(JpaOrder.class);

        // Use join fetch for the category entity
        root.fetch("orderItems", JoinType.LEFT);

        Predicate predicate = spec.toPredicate(root, query, criteriaBuilder);
        query.where(predicate);

        // Sorting
        if (StringUtils.hasLength(condition.getSortBy())) {
            if (condition.isSortAscending()) {
                query.orderBy(criteriaBuilder.asc(root.get(condition.getSortBy())));
            } else {
                query.orderBy(criteriaBuilder.desc(root.get(condition.getSortBy())));
            }
        }

        TypedQuery<JpaOrder> typedQuery = entityManager.createQuery(query);

        // Paging
        if (condition.getOffset() != null && condition.getLimit() != null) {
            typedQuery.setFirstResult(condition.getOffset());
            typedQuery.setMaxResults(condition.getLimit());
        }

        return typedQuery.getResultList();
    }
}
