package org.atlas.framework.search.elasticsearch.repository;

import org.atlas.business.product.infrastructure.contract.search.SearchProductCondition;
import org.atlas.framework.search.elasticsearch.entity.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomEsProductRepository {

    private final ElasticsearchOperations operations;

    public CustomEsProductRepository(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    public Page<EsProduct> searchProduct(SearchProductCondition condition) {
        Query esQuery = buildCriteriaQuery(condition);

        // Sorting
        if (StringUtils.hasLength(condition.getSort())) {
            if (condition.getSort().startsWith("-")) {
                esQuery.addSort(Sort.by(Sort.Order.desc(condition.getSort().substring(1))));
            } else {
                esQuery.addSort(Sort.by(condition.getSort()));
            }
        }

        // Paging
        esQuery.setPageable(PageRequest.of(condition.getPage(), condition.getSize()));

        return executeQuery(esQuery);
    }

    private CriteriaQuery buildCriteriaQuery(SearchProductCondition condition) {
        List<Criteria> criteriaList = new ArrayList<>();
        if (condition.getId() != null) {
            criteriaList.add(Criteria.where("id").is(condition.getId()));
        }
        if (StringUtils.hasLength(condition.getName())) {
            criteriaList.add(Criteria.where("name").fuzzy(condition.getName().toLowerCase()));
        }
        if (condition.getMinPrice() != null) {
            criteriaList.add(Criteria.where("price").greaterThanEqual(condition.getMinPrice()));
        }
        if (condition.getMaxPrice() != null) {
            criteriaList.add(Criteria.where("price").lessThanEqual(condition.getMaxPrice()));
        }
        if (Boolean.TRUE.equals(condition.getInStock())) {
            criteriaList.add(Criteria.where("quantity").greaterThan(0));
        }
        if (condition.getFeatured() != null) {
            criteriaList.add(Criteria.where("featured").is(condition.getInStock()));
        }
        if (condition.getStatus() != null) {
            criteriaList.add(Criteria.where("status").is(condition.getStatus()));
        }
        if (condition.getCategoryId() != null) {
            criteriaList.add(Criteria.where("category.id").is(condition.getCategoryId()));
        }
        if (condition.getStartCreatedAt() != null) {
            criteriaList.add(Criteria.where("createdAt").greaterThanEqual(condition.getStartCreatedAt()));
        }
        if (condition.getEndCreatedAt() != null) {
            criteriaList.add(Criteria.where("createdAt").lessThan(condition.getEndCreatedAt()));
        }
        if (criteriaList.isEmpty()) {
            return new CriteriaQuery(new Criteria());
        }
        return new CriteriaQuery(new Criteria().and(criteriaList.toArray(new Criteria[0])));
    }

    private Page<EsProduct> executeQuery(Query query) {
        SearchHits<EsProduct> searchHits = operations.search(query, EsProduct.class);
        List<EsProduct> products = searchHits.getSearchHits()
            .stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
        long totalHits = searchHits.getTotalHits();
        return PageableExecutionUtils.getPage(products, query.getPageable(), () -> totalHits);
    }
}
