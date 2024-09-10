package org.atlas.framework.grpc.client.user;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.atlas.business.user.application.contract.model.UserAuthDto;
import org.atlas.business.user.domain.shared.enums.Role;
import org.atlas.framework.api.client.contract.user.IUserAuthServiceClient;
import org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto;
import org.atlas.framework.grpc.protobuf.user.UserAuthProto;
import org.atlas.framework.grpc.protobuf.user.UserAuthServiceGrpc;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthAuthServiceClient implements IUserAuthServiceClient {

    @GrpcClient("user")
    private UserAuthServiceGrpc.UserAuthServiceBlockingStub userAuthServiceBlockingStub;

    @Override
    public Optional<UserAuthDto> getUserAuth(String email) {
        GetUserAuthRequestProto requestProto = map(email);
        UserAuthProto responseProto = userAuthServiceBlockingStub.getUserAuth(requestProto);
        return Optional.of(map(responseProto));
    }

    private GetUserAuthRequestProto map(String email) {
        return GetUserAuthRequestProto.newBuilder()
            .setEmail(email)
            .build();
    }

    private UserAuthDto map(UserAuthProto userAuthProto) {
        UserAuthDto userAuthDto = new UserAuthDto();
        userAuthDto.setId(userAuthProto.getId());
        userAuthDto.setEmail(userAuthProto.getEmail());
        userAuthDto.setPassword(userAuthProto.getPassword());
        userAuthDto.setRole(Role.valueOf(userAuthProto.getRole()));
        return userAuthDto;
    }
}
