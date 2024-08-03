package org.atlas.framework.rest.client.resttemplate.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.aggregator.application.contract.model.OrderAgg;
import org.atlas.framework.api.client.contract.IAggregatorServiceClient;
import org.atlas.framework.rest.client.contract.aggregator.GetOrderResponse;
import org.atlas.framework.rest.client.resttemplate.core.RestTemplateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AggregatorServiceClient implements IAggregatorServiceClient {

    @Value("${app.internal.aggregator.base-url:http://localhost:8084}")
    private String baseUrl;

    private final RestTemplateService service;

    @Override
    public Optional<OrderAgg> getOrder(Integer id) {
        String url = String.format("%s/api/orders/%d", baseUrl, id);
        GetOrderResponse response = service.doGet(url, null, null, GetOrderResponse.class);
        return Optional.of(response.getData());
    }
}
