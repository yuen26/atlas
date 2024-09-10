package org.atlas.framework.rest.client.feign.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.user.application.contract.model.UserAuthDto;
import org.atlas.framework.api.client.contract.user.IUserAuthServiceClient;
import org.atlas.framework.rest.client.contract.user.GetUserAuthResponse;
import org.atlas.framework.rest.client.feign.client.UserFeignClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthServiceClient implements IUserAuthServiceClient {

    private final UserFeignClient feignClient;

    @Override
    public Optional<UserAuthDto> getUserAuth(String email) {
        GetUserAuthResponse response = feignClient.getUserAuth(email);
        return Optional.of(response.getData());
    }
}
