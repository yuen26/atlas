package org.atlas.framework.rest.client.resttemplate.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.user.application.contract.model.UserAuthDto;
import org.atlas.framework.api.client.contract.user.IUserAuthServiceClient;
import org.atlas.framework.rest.client.contract.user.GetUserAuthResponse;
import org.atlas.framework.rest.client.resttemplate.core.RestTemplateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthServiceClient implements IUserAuthServiceClient {

    @Value("${app.internal.user.base-url:http://localhost:8081}")
    private String baseUrl;

    private final RestTemplateService service;

    @Override
    public Optional<UserAuthDto> getUserAuth(String email) {
        String url = String.format("%s/api/users/auth", baseUrl);

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("email", email);

        GetUserAuthResponse response = service.doGet(url, paramsMap, null, GetUserAuthResponse.class);
        return Optional.of(response.getData());
    }
}
