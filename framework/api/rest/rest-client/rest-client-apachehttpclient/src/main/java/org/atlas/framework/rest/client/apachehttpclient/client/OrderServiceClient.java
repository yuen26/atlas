package org.atlas.framework.rest.client.apachehttpclient.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.framework.api.client.contract.IOrderServiceClient;
import org.atlas.framework.rest.client.apachehttpclient.core.HttpClientService;
import org.atlas.framework.rest.client.contract.order.GetOrderResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceClient implements IOrderServiceClient {

    @Value("${app.internal.order.base-url:http://localhost:8083}")
    private String baseUrl;

    private final HttpClientService service;

    @Override
    public Optional<OrderDto> getOrder(Integer id) {
        String url = String.format("%s/api/orders/%d", baseUrl, id);
        GetOrderResponse response = service.doGet(url, null, null, GetOrderResponse.class);
        return Optional.of(response.getData());
    }
}
