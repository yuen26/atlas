package org.atlas.framework.rest.client.resttemplate.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.framework.api.client.contract.IProductServiceClient;
import org.atlas.framework.rest.client.contract.product.ListProductByIdsResponse;
import org.atlas.framework.rest.client.resttemplate.core.RestTemplateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceClient implements IProductServiceClient {

    @Value("${app.internal.product.base-url:http://localhost:8082}")
    private String baseUrl;

    private final RestTemplateService service;

    @Override
    public List<ProductDto> listProductByIds(List<Integer> ids) {
        String url = String.format("%s/api/products/list-by-ids", baseUrl);

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("ids", StringUtils.join(ids, ","));

        ListProductByIdsResponse response = service.doGet(url, paramsMap, null, ListProductByIdsResponse.class);
        return response.getData();
    }
}
