package org.atlas.framework.grpc.protobuf.user;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: user.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UserAuthServiceGrpc {

  private UserAuthServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "user.UserAuthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto,
      org.atlas.framework.grpc.protobuf.user.UserAuthProto> getGetUserAuthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserAuth",
      requestType = org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.user.UserAuthProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto,
      org.atlas.framework.grpc.protobuf.user.UserAuthProto> getGetUserAuthMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto, org.atlas.framework.grpc.protobuf.user.UserAuthProto> getGetUserAuthMethod;
    if ((getGetUserAuthMethod = UserAuthServiceGrpc.getGetUserAuthMethod) == null) {
      synchronized (UserAuthServiceGrpc.class) {
        if ((getGetUserAuthMethod = UserAuthServiceGrpc.getGetUserAuthMethod) == null) {
          UserAuthServiceGrpc.getGetUserAuthMethod = getGetUserAuthMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto, org.atlas.framework.grpc.protobuf.user.UserAuthProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserAuth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.user.UserAuthProto.getDefaultInstance()))
              .setSchemaDescriptor(new UserAuthServiceMethodDescriptorSupplier("GetUserAuth"))
              .build();
        }
      }
    }
    return getGetUserAuthMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserAuthServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserAuthServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserAuthServiceStub>() {
        @java.lang.Override
        public UserAuthServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserAuthServiceStub(channel, callOptions);
        }
      };
    return UserAuthServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserAuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserAuthServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserAuthServiceBlockingStub>() {
        @java.lang.Override
        public UserAuthServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserAuthServiceBlockingStub(channel, callOptions);
        }
      };
    return UserAuthServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserAuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserAuthServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserAuthServiceFutureStub>() {
        @java.lang.Override
        public UserAuthServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserAuthServiceFutureStub(channel, callOptions);
        }
      };
    return UserAuthServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getUserAuth(org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.UserAuthProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserAuthMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service UserAuthService.
   */
  public static abstract class UserAuthServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return UserAuthServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service UserAuthService.
   */
  public static final class UserAuthServiceStub
      extends io.grpc.stub.AbstractAsyncStub<UserAuthServiceStub> {
    private UserAuthServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserAuthServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserAuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void getUserAuth(org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.UserAuthProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserAuthMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service UserAuthService.
   */
  public static final class UserAuthServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UserAuthServiceBlockingStub> {
    private UserAuthServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserAuthServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserAuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.user.UserAuthProto getUserAuth(org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserAuthMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service UserAuthService.
   */
  public static final class UserAuthServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<UserAuthServiceFutureStub> {
    private UserAuthServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserAuthServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserAuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.user.UserAuthProto> getUserAuth(
        org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserAuthMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_USER_AUTH = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER_AUTH:
          serviceImpl.getUserAuth((org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.UserAuthProto>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetUserAuthMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.user.GetUserAuthRequestProto,
              org.atlas.framework.grpc.protobuf.user.UserAuthProto>(
                service, METHODID_GET_USER_AUTH)))
        .build();
  }

  private static abstract class UserAuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserAuthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.atlas.framework.grpc.protobuf.user.User.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserAuthService");
    }
  }

  private static final class UserAuthServiceFileDescriptorSupplier
      extends UserAuthServiceBaseDescriptorSupplier {
    UserAuthServiceFileDescriptorSupplier() {}
  }

  private static final class UserAuthServiceMethodDescriptorSupplier
      extends UserAuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    UserAuthServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserAuthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserAuthServiceFileDescriptorSupplier())
              .addMethod(getGetUserAuthMethod())
              .build();
        }
      }
    }
    return result;
  }
}
