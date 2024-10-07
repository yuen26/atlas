package org.atlas.framework.rest.client.feign.client;

import org.atlas.framework.rest.client.contract.customer.ListCustomerResponse;
import org.atlas.framework.rest.client.feign.core.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
    name = "customer-service",
    url = "${app.rest.customer.base-url:http://localhost:8081}",
    configuration = FeignConfig.class
)
public interface CustomerFeignClient {

    @GetMapping("/api/customers")
    ListCustomerResponse listCustomer(@RequestParam("userIds") List<Integer> userIds);
}
