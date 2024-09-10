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
        return find(spec, condition.getSort(), condition.getPage(), condition.getSize());
    }

    @Override
    public long count(FindOrderCondition condition) {
        Specification<JpaOrder> spec = buildSpec(condition);
        return count(spec);
    }

    private Specification<JpaOrder> buildSpec(FindOrderCondition condition) {
        QuerySpecification<JpaOrder> spec = new QuerySpecification<>();
        if (condition.getOrderId() != null) {
            spec.addFilter(QueryFilter.of("orderId", condition.getOrderId(), QueryOperation.EQUAL));
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
        if (condition.getDeleted() != null) {
            spec.addFilter(QueryFilter.of("deleted", condition.getDeleted(), QueryOperation.EQUAL));
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

    private List<JpaOrder> find(Specification<JpaOrder> spec, String sort, Integer pageNumber, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaOrder> query = criteriaBuilder.createQuery(JpaOrder.class);
        Root<JpaOrder> root = query.from(JpaOrder.class);

        // Use join fetch for the category entity
        root.fetch("orderItems", JoinType.LEFT);

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

        TypedQuery<JpaOrder> typedQuery = entityManager.createQuery(query);

        // Paging
        if (pageNumber != null && pageSize != null) {
            typedQuery.setFirstResult(pageNumber * pageSize);
            typedQuery.setMaxResults(pageSize);
        }

        return typedQuery.getResultList();
    }
}
