package org.atlas.framework.grpc.server.user;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.atlas.business.user.application.contract.command.CreateCustomerCommand;
import org.atlas.business.user.application.contract.command.GetLoginUserCommand;
import org.atlas.business.user.application.contract.command.GetUserCommand;
import org.atlas.business.user.application.contract.model.LoginUserDto;
import org.atlas.business.user.application.contract.model.UserDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto;
import org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto;
import org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto;
import org.atlas.framework.grpc.protobuf.user.GetUserRequestProto;
import org.atlas.framework.grpc.protobuf.user.LoginUserProto;
import org.atlas.framework.grpc.protobuf.user.UserProto;
import org.atlas.framework.grpc.protobuf.user.UserServiceGrpc;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final CommandGateway commandGateway;

    @Override
    public void getUser(GetUserRequestProto requestProto,
                        StreamObserver<UserProto> responseObserver) {
        GetUserCommand command = map(requestProto);
        try {
            UserDto userDto = commandGateway.send(command);
            UserProto responseProto = map(userDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getLoginUser(GetLoginUserRequestProto requestProto,
                             StreamObserver<LoginUserProto> responseObserver) {
        GetLoginUserCommand command = map(requestProto);
        try {
            LoginUserDto loginUserDto = commandGateway.send(command);
            LoginUserProto responseProto = map(loginUserDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createCustomer(CreateCustomerRequestProto requestProto,
                              StreamObserver<CreateCustomerResponseProto> responseObserver) {
        CreateCustomerCommand command = map(requestProto);
        try {
            Integer id = commandGateway.send(command);
            CreateCustomerResponseProto responseProto = map(id);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private GetUserCommand map(GetUserRequestProto requestProto) {
        return new GetUserCommand(requestProto.getId());
    }

    private UserProto map(UserDto userDto) {
        return UserProto.newBuilder()
            .setId(userDto.getId())
            .setUsername(userDto.getUsername())
            .setEmail(userDto.getEmail())
            .setRole(userDto.getRole().name())
            .build();
    }

    private GetLoginUserCommand map(GetLoginUserRequestProto requestProto) {
        return new GetLoginUserCommand(requestProto.getEmail());
    }

    private LoginUserProto map(LoginUserDto loginUserDto) {
        return LoginUserProto.newBuilder()
            .setId(loginUserDto.getId())
            .setEmail(loginUserDto.getEmail())
            .setPassword(loginUserDto.getPassword())
            .build();
    }

    private CreateCustomerCommand map(CreateCustomerRequestProto requestProto) {
        CreateCustomerCommand command = new CreateCustomerCommand();
        command.setUsername(requestProto.getUsername());
        command.setEmail(requestProto.getEmail());
        command.setPassword(requestProto.getPassword());
        return command;
    }

    private CreateCustomerResponseProto map(Integer id) {
        return CreateCustomerResponseProto.newBuilder()
            .setId(id)
            .build();
    }
}
