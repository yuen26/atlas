package org.atlas.framework.rest.client.feign.client;

import org.atlas.framework.rest.client.contract.order.GetOrderResponse;
import org.atlas.framework.rest.client.feign.core.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "order-service",
    url = "${app.internal.order.base-url:http://localhost:8083}",
    configuration = FeignConfig.class
)
public interface OrderFeignClient {

    @GetMapping("/api/orders/{id}")
    GetOrderResponse getOrder(@PathVariable("id") Integer id);
}
