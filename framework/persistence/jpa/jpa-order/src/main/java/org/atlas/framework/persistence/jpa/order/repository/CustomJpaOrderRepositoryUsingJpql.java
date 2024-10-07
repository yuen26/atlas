package org.atlas.framework.persistence.jpa.order.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;
import org.atlas.order.domain.repository.FindOrderCondition;
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
        return find(whereClause, params, condition);
    }

    @Override
    public long count(FindOrderCondition condition) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildWhereClause(condition, params);
        return count(whereClause, params);
    }

    private String buildWhereClause(FindOrderCondition condition, Map<String, Object> params) {
        StringBuilder whereClauseBuilder = new StringBuilder("where 1=1 ");
        if (condition.getId() != null) {
            whereClauseBuilder.append(" and o.id = :id ");
            params.put("id", condition.getId());
        }
        if (condition.getUserId() != null) {
            whereClauseBuilder.append(" and o.userId = :userId ");
            params.put("userId", condition.getUserId());
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

    private List<JpaOrder> find(String whereClause, Map<String, Object> params, FindOrderCondition condition) {
        StringBuilder sqlBuilder = new StringBuilder("select o from JpaOrder o left join fetch o.orderItems ");
        sqlBuilder.append(whereClause);

        // Sorting
        if (StringUtils.hasLength(condition.getSortBy())) {
            sqlBuilder.append(" order by ");
            if (condition.isSortAscending()) {
                sqlBuilder.append(condition.getSortBy());
            } else {
                sqlBuilder.append(condition.getSortBy()).append(" desc");
            }
        }

        String sql = sqlBuilder.toString();
        TypedQuery<JpaOrder> query = entityManager.createQuery(sql, JpaOrder.class);
        params.forEach(query::setParameter);

        // Paging
        if (condition.getOffset() != null && condition.getLimit() != null) {
            query.setFirstResult(condition.getOffset());
            query.setMaxResults(condition.getLimit());
        }

        return query.getResultList();
    }
}
