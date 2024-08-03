package org.atlas.framework.rest.client.feign.client;

import org.atlas.framework.rest.client.contract.user.GetLoginUserResponse;
import org.atlas.framework.rest.client.contract.user.GetUserResponse;
import org.atlas.framework.rest.client.feign.core.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "user-service",
    url = "${app.internal.user.base-url:http://localhost:8081}",
    configuration = FeignConfig.class
)
public interface UserFeignClient {

    @GetMapping("/api/users/{id}")
    GetUserResponse getUser(@PathVariable("id") Integer id);

    @GetMapping("/api/users/login-user")
    GetLoginUserResponse getLoginUser(@RequestParam("email") String email);
}
