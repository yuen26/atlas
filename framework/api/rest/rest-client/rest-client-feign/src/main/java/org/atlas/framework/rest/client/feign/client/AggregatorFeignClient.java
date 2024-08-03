package org.atlas.framework.rest.client.feign.client;

import org.atlas.framework.rest.client.contract.aggregator.GetOrderResponse;
import org.atlas.framework.rest.client.feign.core.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "aggregator-service",
    url = "${app.internal.aggregator.base-url:http://localhost:8084}",
    configuration = FeignConfig.class
)
public interface AggregatorFeignClient {

    @GetMapping("/api/orders/{id}")
    GetOrderResponse getOrder(@PathVariable("id") Integer id);
}
