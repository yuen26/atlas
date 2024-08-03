package org.atlas.framework.search.elasticsearch.repository;

import org.atlas.framework.search.elasticsearch.entity.EsProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Integer> {
}
