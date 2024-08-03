package org.atlas.framework.rest.client.resttemplate.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.user.application.contract.model.LoginUserDto;
import org.atlas.business.user.application.contract.model.UserDto;
import org.atlas.framework.api.client.contract.IUserServiceClient;
import org.atlas.framework.rest.client.contract.user.GetLoginUserResponse;
import org.atlas.framework.rest.client.contract.user.GetUserResponse;
import org.atlas.framework.rest.client.resttemplate.core.RestTemplateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceClient implements IUserServiceClient {

    @Value("${app.internal.user.base-url:http://localhost:8081}")
    private String baseUrl;

    private final RestTemplateService service;

    @Override
    public Optional<UserDto> getUser(Integer id) {
        String url = String.format("%s/api/users/%d", baseUrl, id);

        GetUserResponse response = service.doGet(url, null, null, GetUserResponse.class);
        return Optional.of(response.getData());
    }

    @Override
    public Optional<LoginUserDto> getLoginUser(String email) {
        String url = String.format("%s/api/users/login-user", baseUrl);

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("email", email);

        GetLoginUserResponse response = service.doGet(url, paramsMap, null, GetLoginUserResponse.class);
        return Optional.of(response.getData());
    }
}
