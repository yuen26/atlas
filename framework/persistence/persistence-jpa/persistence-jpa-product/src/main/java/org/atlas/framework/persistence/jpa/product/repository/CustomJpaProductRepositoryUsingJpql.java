package org.atlas.framework.persistence.jpa.product.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.atlas.business.product.domain.repository.FindProductCondition;
import org.atlas.framework.persistence.jpa.product.entity.JpaProduct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class CustomJpaProductRepositoryUsingJpql implements CustomJpaProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<JpaProduct> find(FindProductCondition condition) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildWhereClause(condition, params);
        return find(whereClause, condition.getSort(), condition.getPage(), condition.getSize(), params);
    }

    @Override
    public long count(FindProductCondition condition) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildWhereClause(condition, params);
        return count(whereClause, params);
    }

    private String buildWhereClause(FindProductCondition condition, Map<String, Object> params) {
        StringBuilder whereClauseBuilder = new StringBuilder("where 1=1 ");
        if (condition.getId() != null) {
            whereClauseBuilder.append(" and p.id = :id ");
            params.put("id", condition.getId());
        }
        if (StringUtils.hasLength(condition.getName())) {
            whereClauseBuilder.append(" and lower(p.name) like :name ");
            params.put("name", "%" + condition.getName().toLowerCase() + "%");
        }
        if (condition.getCategoryId() != null) {
            whereClauseBuilder.append(" and p.category.id = :categoryId ");
            params.put("categoryId", condition.getCategoryId());
        }
        if (condition.getMinPrice() != null) {
            whereClauseBuilder.append(" and p.price >= :minPrice ");
            params.put("minPrice", condition.getMinPrice());
        }
        if (condition.getMaxPrice() != null) {
            whereClauseBuilder.append(" and p.price <= :maxPrice ");
            params.put("maxPrice", condition.getMaxPrice());
        }
        if (Boolean.TRUE.equals(condition.getInStock())) {
            whereClauseBuilder.append(" and p.quantity > 0 ");
        }
        if (condition.getStatus() != null) {
            whereClauseBuilder.append(" and p.status = :status ");
            params.put("status", condition.getStatus());
        }
        if (condition.getFeatured() != null) {
            whereClauseBuilder.append(" and p.featured = :featured ");
            params.put("featured", condition.getFeatured());
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
        String countSql = "select count(p.id) from JpaProduct p " + whereClause;
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);
        params.forEach(countQuery::setParameter);
        return countQuery.getSingleResult();
    }

    private List<JpaProduct> find(String whereClause, String sort, Integer pageNumber, Integer pageSize, Map<String, Object> params) {
        StringBuilder selectBuilder = new StringBuilder("select p from JpaProduct p left join fetch p.category ");
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

        String selectSql = selectBuilder.toString();
        TypedQuery<JpaProduct> selectQuery = entityManager.createQuery(selectSql, JpaProduct.class);
        params.forEach(selectQuery::setParameter);

        // Paging
        if (pageNumber != null && pageSize != null) {
            selectQuery.setFirstResult(pageNumber * pageSize);
            selectQuery.setMaxResults(pageSize);
        }

        return selectQuery.getResultList();
    }
}
