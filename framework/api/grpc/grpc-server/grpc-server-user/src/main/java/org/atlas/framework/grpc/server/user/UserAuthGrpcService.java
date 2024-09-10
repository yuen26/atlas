package org.atlas.framework.grpc.server.user;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.atlas.business.user.application.contract.command.GetUserAuthCommand;
import org.atlas.business.user.application.contract.model.UserAuthDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto;
import org.atlas.framework.grpc.protobuf.user.UserAuthProto;
import org.atlas.framework.grpc.protobuf.user.UserAuthServiceGrpc;

@GrpcService
@RequiredArgsConstructor
public class UserAuthGrpcService extends UserAuthServiceGrpc.UserAuthServiceImplBase {

    private final CommandGateway commandGateway;

    @Override
    public void getUserAuth(GetUserAuthRequestProto requestProto,
                            StreamObserver<UserAuthProto> responseObserver) {
        GetUserAuthCommand command = map(requestProto);
        try {
            UserAuthDto userAuthDto = commandGateway.send(command);
            UserAuthProto responseProto = map(userAuthDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private GetUserAuthCommand map(GetUserAuthRequestProto requestProto) {
        return new GetUserAuthCommand(requestProto.getEmail());
    }

    private UserAuthProto map(UserAuthDto userAuthDto) {
        return UserAuthProto.newBuilder()
            .setId(userAuthDto.getId())
            .setEmail(userAuthDto.getEmail())
            .setPassword(userAuthDto.getPassword())
            .build();
    }
}
