package org.atlas.framework.rest.client.feign.client;

import org.atlas.framework.rest.client.contract.user.GetUserAuthResponse;
import org.atlas.framework.rest.client.contract.user.ListCustomerResponse;
import org.atlas.framework.rest.client.feign.core.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
    name = "user-service",
    url = "${app.internal.user.base-url:http://localhost:8081}",
    configuration = FeignConfig.class
)
public interface UserFeignClient {

    @GetMapping("/api/users/auth")
    GetUserAuthResponse getUserAuth(@RequestParam("email") String email);

    @GetMapping("/api/customers")
    ListCustomerResponse listCustomer(@RequestParam("ids") List<Integer> ids);
}
