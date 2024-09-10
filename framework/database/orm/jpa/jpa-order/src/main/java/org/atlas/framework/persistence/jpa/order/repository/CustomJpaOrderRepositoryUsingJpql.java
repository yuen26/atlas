package org.atlas.framework.persistence.jpa.order.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.atlas.business.order.domain.repository.FindOrderCondition;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class CustomJpaOrderRepositoryUsingJpql implements CustomJpaOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<JpaOrder> find(FindOrderCondition condition) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildWhereClause(condition, params);
        return find(whereClause, condition.getSort(), condition.getPage(), condition.getSize(), params);
    }

    @Override
    public long count(FindOrderCondition condition) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildWhereClause(condition, params);
        return count(whereClause, params);
    }

    private String buildWhereClause(FindOrderCondition condition, Map<String, Object> params) {
        StringBuilder whereClauseBuilder = new StringBuilder("where 1=1 ");
        if (condition.getOrderId() != null) {
            whereClauseBuilder.append(" and o.orderId = :orderId ");
            params.put("orderId", condition.getOrderId());
        }
        if (condition.getCustomerId() != null) {
            whereClauseBuilder.append(" and o.customerId = :customerId ");
            params.put("customerId", condition.getCustomerId());
        }
        if (condition.getMinAmount() != null) {
            whereClauseBuilder.append(" and o.amount >= :minAmount ");
            params.put("minAmount", condition.getMinAmount());
        }
        if (condition.getMaxAmount() != null) {
            whereClauseBuilder.append(" and o.amount <= :maxAmount ");
            params.put("maxAmount", condition.getMaxAmount());
        }
        if (StringUtils.hasLength(condition.getAddress())) {
            whereClauseBuilder.append(" and lower(o.address) like :address ");
            params.put("address", "%" + condition.getAddress().toLowerCase() + "%");
        }
        if (condition.getStatus() != null) {
            whereClauseBuilder.append(" and p.status = :status ");
            params.put("status", condition.getStatus());
        }
        if (condition.getDeleted() != null) {
            whereClauseBuilder.append(" and o.deleted = :deleted ");
            params.put("deleted", condition.getDeleted());
        }
        if (condition.getStartCreatedAt() != null) {
            whereClauseBuilder.append(" and p.createdAt >= :startCreatedAt ");
            params.put("startCreatedAt", condition.getStartCreatedAt());
        }
        if (condition.getEndCreatedAt() != null) {
            whereClauseBuilder.append(" and p.createdAt <= :endCreatedAt ");
            params.put("endCreatedAt", condition.getEndCreatedAt());
        }
        return whereClauseBuilder.toString();
    }

    private Long count(String whereClause,  Map<String, Object> params) {
        String sql = "select count(o.orderId) from JpaOrder o " + whereClause;
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
        params.forEach(query::setParameter);
        return query.getSingleResult();
    }

    private List<JpaOrder> find(String whereClause, String sort, Integer pageNumber, Integer pageSize, Map<String, Object> params) {
        StringBuilder selectBuilder = new StringBuilder("select o from JpaOrder o left join fetch o.orderItems ");
        selectBuilder.append(whereClause);

        // Sorting
        if (StringUtils.hasLength(sort)) {
            selectBuilder.append(" order by ");
            if (sort.startsWith("-")) {
                selectBuilder.append(sort.substring(1)).append(" desc");
            } else {
                selectBuilder.append(sort);
            }
        }

        String sql = selectBuilder.toString();
        TypedQuery<JpaOrder> query = entityManager.createQuery(sql, JpaOrder.class);
        params.forEach(query::setParameter);

        // Paging
        if (pageNumber != null && pageSize != null) {
            query.setFirstResult(pageNumber * pageSize);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }
}
