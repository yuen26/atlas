package org.atlas.framework.grpc.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.atlas.business.user.application.contract.model.LoginUserDto;
import org.atlas.business.user.application.contract.model.UserDto;
import org.atlas.business.user.domain.shared.enums.Role;
import org.atlas.framework.api.client.contract.IUserServiceClient;
import org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto;
import org.atlas.framework.grpc.protobuf.user.GetUserRequestProto;
import org.atlas.framework.grpc.protobuf.user.LoginUserProto;
import org.atlas.framework.grpc.protobuf.user.UserProto;
import org.atlas.framework.grpc.protobuf.user.UserServiceGrpc;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceClient implements IUserServiceClient {

    @GrpcClient("user")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @Override
    public Optional<UserDto> getUser(Integer id) {
        GetUserRequestProto requestProto = map(id);
        UserProto responseProto = userServiceBlockingStub.getUser(requestProto);
        return Optional.of(map(responseProto));
    }

    @Override
    public Optional<LoginUserDto> getLoginUser(String email) {
        GetLoginUserRequestProto requestProto = map(email);
        LoginUserProto responseProto = userServiceBlockingStub.getLoginUser(requestProto);
        return Optional.of(map(responseProto));
    }

    private GetUserRequestProto map(Integer id) {
        return GetUserRequestProto.newBuilder()
            .setId(id)
            .build();
    }

    private UserDto map(UserProto userProto) {
        UserDto userDto = new UserDto();
        userDto.setId(userProto.getId());
        userDto.setUsername(userProto.getUsername());
        userDto.setEmail(userProto.getEmail());
        userDto.setRole(Role.valueOf(userProto.getRole()));
        return userDto;
    }

    private GetLoginUserRequestProto map(String email) {
        return GetLoginUserRequestProto.newBuilder()
            .setEmail(email)
            .build();
    }

    private LoginUserDto map(LoginUserProto loginUserProto) {
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setId(loginUserProto.getId());
        loginUserDto.setEmail(loginUserProto.getEmail());
        loginUserDto.setPassword(loginUserProto.getPassword());
        loginUserDto.setRole(Role.valueOf(loginUserProto.getRole()));
        return loginUserDto;
    }
}
