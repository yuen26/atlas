package org.atlas.framework.grpc.protobuf.user;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: user.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "user.UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.GetUserRequestProto,
      org.atlas.framework.grpc.protobuf.user.UserProto> getGetUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUser",
      requestType = org.atlas.framework.grpc.protobuf.user.GetUserRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.user.UserProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.GetUserRequestProto,
      org.atlas.framework.grpc.protobuf.user.UserProto> getGetUserMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.GetUserRequestProto, org.atlas.framework.grpc.protobuf.user.UserProto> getGetUserMethod;
    if ((getGetUserMethod = UserServiceGrpc.getGetUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetUserMethod = UserServiceGrpc.getGetUserMethod) == null) {
          UserServiceGrpc.getGetUserMethod = getGetUserMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.user.GetUserRequestProto, org.atlas.framework.grpc.protobuf.user.UserProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.user.GetUserRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.user.UserProto.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetUser"))
              .build();
        }
      }
    }
    return getGetUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto,
      org.atlas.framework.grpc.protobuf.user.LoginUserProto> getGetLoginUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLoginUser",
      requestType = org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.user.LoginUserProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto,
      org.atlas.framework.grpc.protobuf.user.LoginUserProto> getGetLoginUserMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto, org.atlas.framework.grpc.protobuf.user.LoginUserProto> getGetLoginUserMethod;
    if ((getGetLoginUserMethod = UserServiceGrpc.getGetLoginUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetLoginUserMethod = UserServiceGrpc.getGetLoginUserMethod) == null) {
          UserServiceGrpc.getGetLoginUserMethod = getGetLoginUserMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto, org.atlas.framework.grpc.protobuf.user.LoginUserProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLoginUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.user.LoginUserProto.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetLoginUser"))
              .build();
        }
      }
    }
    return getGetLoginUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto,
      org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto> getCreateCustomerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateCustomer",
      requestType = org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto,
      org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto> getCreateCustomerMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto, org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto> getCreateCustomerMethod;
    if ((getCreateCustomerMethod = UserServiceGrpc.getCreateCustomerMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getCreateCustomerMethod = UserServiceGrpc.getCreateCustomerMethod) == null) {
          UserServiceGrpc.getCreateCustomerMethod = getCreateCustomerMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto, org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateCustomer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("CreateCustomer"))
              .build();
        }
      }
    }
    return getCreateCustomerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceStub>() {
        @java.lang.Override
        public UserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceStub(channel, callOptions);
        }
      };
    return UserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub>() {
        @java.lang.Override
        public UserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceBlockingStub(channel, callOptions);
        }
      };
    return UserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub>() {
        @java.lang.Override
        public UserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceFutureStub(channel, callOptions);
        }
      };
    return UserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getUser(org.atlas.framework.grpc.protobuf.user.GetUserRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.UserProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserMethod(), responseObserver);
    }

    /**
     */
    default void getLoginUser(org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.LoginUserProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetLoginUserMethod(), responseObserver);
    }

    /**
     */
    default void createCustomer(org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateCustomerMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service UserService.
   */
  public static abstract class UserServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return UserServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service UserService.
   */
  public static final class UserServiceStub
      extends io.grpc.stub.AbstractAsyncStub<UserServiceStub> {
    private UserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void getUser(org.atlas.framework.grpc.protobuf.user.GetUserRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.UserProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLoginUser(org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.LoginUserProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetLoginUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createCustomer(org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateCustomerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service UserService.
   */
  public static final class UserServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.user.UserProto getUser(org.atlas.framework.grpc.protobuf.user.GetUserRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.user.LoginUserProto getLoginUser(org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetLoginUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto createCustomer(org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateCustomerMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service UserService.
   */
  public static final class UserServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<UserServiceFutureStub> {
    private UserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.user.UserProto> getUser(
        org.atlas.framework.grpc.protobuf.user.GetUserRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.user.LoginUserProto> getLoginUser(
        org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetLoginUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto> createCustomer(
        org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateCustomerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_USER = 0;
  private static final int METHODID_GET_LOGIN_USER = 1;
  private static final int METHODID_CREATE_CUSTOMER = 2;

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
        case METHODID_GET_USER:
          serviceImpl.getUser((org.atlas.framework.grpc.protobuf.user.GetUserRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.UserProto>) responseObserver);
          break;
        case METHODID_GET_LOGIN_USER:
          serviceImpl.getLoginUser((org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.LoginUserProto>) responseObserver);
          break;
        case METHODID_CREATE_CUSTOMER:
          serviceImpl.createCustomer((org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto>) responseObserver);
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
          getGetUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.user.GetUserRequestProto,
              org.atlas.framework.grpc.protobuf.user.UserProto>(
                service, METHODID_GET_USER)))
        .addMethod(
          getGetLoginUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.user.GetLoginUserRequestProto,
              org.atlas.framework.grpc.protobuf.user.LoginUserProto>(
                service, METHODID_GET_LOGIN_USER)))
        .addMethod(
          getCreateCustomerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.user.CreateCustomerRequestProto,
              org.atlas.framework.grpc.protobuf.user.CreateCustomerResponseProto>(
                service, METHODID_CREATE_CUSTOMER)))
        .build();
  }

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.atlas.framework.grpc.protobuf.user.User.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    UserServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getGetUserMethod())
              .addMethod(getGetLoginUserMethod())
              .addMethod(getCreateCustomerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
