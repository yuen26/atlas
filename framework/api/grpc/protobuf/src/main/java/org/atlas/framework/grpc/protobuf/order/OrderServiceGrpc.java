package org.atlas.framework.grpc.protobuf.order;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: order.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class OrderServiceGrpc {

  private OrderServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "order.OrderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto,
      org.atlas.framework.grpc.protobuf.order.OrderPageProto> getListOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListOrder",
      requestType = org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.order.OrderPageProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto,
      org.atlas.framework.grpc.protobuf.order.OrderPageProto> getListOrderMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto, org.atlas.framework.grpc.protobuf.order.OrderPageProto> getListOrderMethod;
    if ((getListOrderMethod = OrderServiceGrpc.getListOrderMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getListOrderMethod = OrderServiceGrpc.getListOrderMethod) == null) {
          OrderServiceGrpc.getListOrderMethod = getListOrderMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto, org.atlas.framework.grpc.protobuf.order.OrderPageProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.OrderPageProto.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("ListOrder"))
              .build();
        }
      }
    }
    return getListOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto,
      org.atlas.framework.grpc.protobuf.order.OrderProto> getGetOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOrder",
      requestType = org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.order.OrderProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto,
      org.atlas.framework.grpc.protobuf.order.OrderProto> getGetOrderMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto, org.atlas.framework.grpc.protobuf.order.OrderProto> getGetOrderMethod;
    if ((getGetOrderMethod = OrderServiceGrpc.getGetOrderMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getGetOrderMethod = OrderServiceGrpc.getGetOrderMethod) == null) {
          OrderServiceGrpc.getGetOrderMethod = getGetOrderMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto, org.atlas.framework.grpc.protobuf.order.OrderProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.OrderProto.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("GetOrder"))
              .build();
        }
      }
    }
    return getGetOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto,
      org.atlas.framework.grpc.protobuf.order.OrderStatusProto> getGetOrderStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOrderStatus",
      requestType = org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.order.OrderStatusProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto,
      org.atlas.framework.grpc.protobuf.order.OrderStatusProto> getGetOrderStatusMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto, org.atlas.framework.grpc.protobuf.order.OrderStatusProto> getGetOrderStatusMethod;
    if ((getGetOrderStatusMethod = OrderServiceGrpc.getGetOrderStatusMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getGetOrderStatusMethod = OrderServiceGrpc.getGetOrderStatusMethod) == null) {
          OrderServiceGrpc.getGetOrderStatusMethod = getGetOrderStatusMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto, org.atlas.framework.grpc.protobuf.order.OrderStatusProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetOrderStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.OrderStatusProto.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("GetOrderStatus"))
              .build();
        }
      }
    }
    return getGetOrderStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto,
      com.google.protobuf.Empty> getCreateOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateOrder",
      requestType = org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto,
      com.google.protobuf.Empty> getCreateOrderMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto, com.google.protobuf.Empty> getCreateOrderMethod;
    if ((getCreateOrderMethod = OrderServiceGrpc.getCreateOrderMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getCreateOrderMethod = OrderServiceGrpc.getCreateOrderMethod) == null) {
          OrderServiceGrpc.getCreateOrderMethod = getCreateOrderMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("CreateOrder"))
              .build();
        }
      }
    }
    return getCreateOrderMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OrderServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceStub>() {
        @java.lang.Override
        public OrderServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceStub(channel, callOptions);
        }
      };
    return OrderServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OrderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceBlockingStub>() {
        @java.lang.Override
        public OrderServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceBlockingStub(channel, callOptions);
        }
      };
    return OrderServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OrderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceFutureStub>() {
        @java.lang.Override
        public OrderServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceFutureStub(channel, callOptions);
        }
      };
    return OrderServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void listOrder(org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.OrderPageProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListOrderMethod(), responseObserver);
    }

    /**
     */
    default void getOrder(org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.OrderProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOrderMethod(), responseObserver);
    }

    /**
     */
    default void getOrderStatus(org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.OrderStatusProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOrderStatusMethod(), responseObserver);
    }

    /**
     */
    default void createOrder(org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateOrderMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service OrderService.
   */
  public static abstract class OrderServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return OrderServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service OrderService.
   */
  public static final class OrderServiceStub
      extends io.grpc.stub.AbstractAsyncStub<OrderServiceStub> {
    private OrderServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceStub(channel, callOptions);
    }

    /**
     */
    public void listOrder(org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.OrderPageProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOrder(org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.OrderProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOrderStatus(org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.OrderStatusProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOrderStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createOrder(org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateOrderMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service OrderService.
   */
  public static final class OrderServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<OrderServiceBlockingStub> {
    private OrderServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.order.OrderPageProto listOrder(org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.order.OrderProto getOrder(org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.order.OrderStatusProto getOrderStatus(org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOrderStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty createOrder(org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateOrderMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service OrderService.
   */
  public static final class OrderServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<OrderServiceFutureStub> {
    private OrderServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.order.OrderPageProto> listOrder(
        org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.order.OrderProto> getOrder(
        org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.order.OrderStatusProto> getOrderStatus(
        org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOrderStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> createOrder(
        org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateOrderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_ORDER = 0;
  private static final int METHODID_GET_ORDER = 1;
  private static final int METHODID_GET_ORDER_STATUS = 2;
  private static final int METHODID_CREATE_ORDER = 3;

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
        case METHODID_LIST_ORDER:
          serviceImpl.listOrder((org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.OrderPageProto>) responseObserver);
          break;
        case METHODID_GET_ORDER:
          serviceImpl.getOrder((org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.OrderProto>) responseObserver);
          break;
        case METHODID_GET_ORDER_STATUS:
          serviceImpl.getOrderStatus((org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.OrderStatusProto>) responseObserver);
          break;
        case METHODID_CREATE_ORDER:
          serviceImpl.createOrder((org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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
          getListOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto,
              org.atlas.framework.grpc.protobuf.order.OrderPageProto>(
                service, METHODID_LIST_ORDER)))
        .addMethod(
          getGetOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto,
              org.atlas.framework.grpc.protobuf.order.OrderProto>(
                service, METHODID_GET_ORDER)))
        .addMethod(
          getGetOrderStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto,
              org.atlas.framework.grpc.protobuf.order.OrderStatusProto>(
                service, METHODID_GET_ORDER_STATUS)))
        .addMethod(
          getCreateOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto,
              com.google.protobuf.Empty>(
                service, METHODID_CREATE_ORDER)))
        .build();
  }

  private static abstract class OrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OrderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.atlas.framework.grpc.protobuf.order.Order.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OrderService");
    }
  }

  private static final class OrderServiceFileDescriptorSupplier
      extends OrderServiceBaseDescriptorSupplier {
    OrderServiceFileDescriptorSupplier() {}
  }

  private static final class OrderServiceMethodDescriptorSupplier
      extends OrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    OrderServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (OrderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OrderServiceFileDescriptorSupplier())
              .addMethod(getListOrderMethod())
              .addMethod(getGetOrderMethod())
              .addMethod(getGetOrderStatusMethod())
              .addMethod(getCreateOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
