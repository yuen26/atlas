package org.atlas.framework.rest.client.feign.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.aggregator.application.contract.model.OrderAgg;
import org.atlas.framework.api.client.contract.IAggregatorServiceClient;
import org.atlas.framework.rest.client.contract.aggregator.GetOrderResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AggregatorServiceClient implements IAggregatorServiceClient {

    private final AggregatorFeignClient feignClient;

    @Override
    public Optional<OrderAgg> getOrder(Integer id) {
        GetOrderResponse response = feignClient.getOrder(id);
        return Optional.of(response.getData());
    }
}
