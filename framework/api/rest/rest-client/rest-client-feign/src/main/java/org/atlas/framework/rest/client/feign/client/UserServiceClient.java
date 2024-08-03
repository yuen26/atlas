package org.atlas.framework.rest.client.feign.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.user.application.contract.model.LoginUserDto;
import org.atlas.business.user.application.contract.model.UserDto;
import org.atlas.framework.api.client.contract.IUserServiceClient;
import org.atlas.framework.rest.client.contract.user.GetLoginUserResponse;
import org.atlas.framework.rest.client.contract.user.GetUserResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceClient implements IUserServiceClient {

    private final UserFeignClient feignClient;

    @Override
    public Optional<UserDto> getUser(Integer id) {
        GetUserResponse response = feignClient.getUser(id);
        return Optional.of(response.getData());
    }

    @Override
    public Optional<LoginUserDto> getLoginUser(String email) {
        GetLoginUserResponse response = feignClient.getLoginUser(email);
        return Optional.of(response.getData());
    }
}
