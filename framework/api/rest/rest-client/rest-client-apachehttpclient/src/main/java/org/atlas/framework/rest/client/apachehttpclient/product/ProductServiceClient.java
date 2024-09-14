package org.atlas.framework.rest.client.apachehttpclient.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.framework.api.client.contract.product.IProductServiceClient;
import org.atlas.framework.rest.client.apachehttpclient.core.HttpClientService;
import org.atlas.framework.rest.client.contract.product.ListProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceClient implements IProductServiceClient {

    @Value("${app.rest.product.base-url:http://localhost:8082}")
    private String baseUrl;

    private final HttpClientService service;

    @Override
    public List<ProductDto> listProduct(List<Integer> ids) {
        String url = String.format("%s/api/products", baseUrl);

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("ids", StringUtils.join(ids, ","));

        ListProductResponse response = service.doGet(url, paramsMap, null, ListProductResponse.class);
        return response.getData();
    }
}
