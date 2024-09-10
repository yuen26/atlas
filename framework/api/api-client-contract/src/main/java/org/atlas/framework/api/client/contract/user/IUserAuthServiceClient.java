package org.atlas.framework.api.client.contract.user;

import org.atlas.business.user.application.contract.model.UserAuthDto;

import java.util.Optional;

public interface IUserAuthServiceClient {

    Optional<UserAuthDto> getUserAuth(String email);
}
