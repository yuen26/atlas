package org.atlas.framework.api.client.contract;

import org.atlas.business.user.application.contract.model.LoginUserDto;
import org.atlas.business.user.application.contract.model.UserDto;

import java.util.Optional;

public interface IUserServiceClient {

    Optional<UserDto> getUser(Integer id);
    Optional<LoginUserDto> getLoginUser(String email);
}
