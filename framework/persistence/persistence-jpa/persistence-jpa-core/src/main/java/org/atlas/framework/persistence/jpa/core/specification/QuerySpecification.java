package org.atlas.framework.persistence.jpa.core.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuerySpecification<T> implements Specification<T> {

    private final List<QueryFilter> filters = new ArrayList<>();

    public void addFilter(QueryFilter filter) {
        filters.add(filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (QueryFilter filter : filters) {
            Path path = getPath(filter, root);
            switch (filter.getOperation()) {
                case GREATER_THAN:
                    predicates.add(criteriaBuilder.greaterThan(path, (Comparable) filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.add(criteriaBuilder.lessThan(path, (Comparable) filter.getValue()));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(path, (Comparable) filter.getValue()));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(path, (Comparable) filter.getValue()));
                    break;
                case EQUAL:
                    predicates.add(criteriaBuilder.equal(path, filter.getValue()));
                    break;
                case NOT_EQUAL:
                    predicates.add(criteriaBuilder.notEqual(path, filter.getValue()));
                    break;
                case LIKE:
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(path.as(String.class)),
                        "%" + filter.getValue().toString().toLowerCase() + "%"));
                    break;
                case LIKE_START:
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(path.as(String.class)),
                        "%" + filter.getValue().toString().toLowerCase()));
                    break;
                case LIKE_END:
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(path.as(String.class)),
                        filter.getValue().toString().toLowerCase() + "%"));
                    break;
                case IN:
                    predicates.add(path.in((Collection<?>) filter.getValue()));
                    break;
                case NOT_IN:
                    predicates.add(criteriaBuilder.not(path.in((Collection<?>) filter.getValue())));
                    break;
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private <X, Y extends Comparable<? super Y>> Path<Y> getPath(QueryFilter filter, Root<X> root) {
        if (filter.getKey().contains(".")) {
            String[] tokens = filter.getKey().split("\\.");
            return root.join(tokens[0]).get(tokens[1]);
        } else {
            return root.get(filter.getKey());
        }
    }
}
