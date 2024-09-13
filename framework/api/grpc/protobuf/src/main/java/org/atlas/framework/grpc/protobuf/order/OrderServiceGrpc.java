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

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto,
      org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto> getPlaceOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PlaceOrder",
      requestType = org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto,
      org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto> getPlaceOrderMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto, org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto> getPlaceOrderMethod;
    if ((getPlaceOrderMethod = OrderServiceGrpc.getPlaceOrderMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getPlaceOrderMethod = OrderServiceGrpc.getPlaceOrderMethod) == null) {
          OrderServiceGrpc.getPlaceOrderMethod = getPlaceOrderMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto, org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PlaceOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("PlaceOrder"))
              .build();
        }
      }
    }
    return getPlaceOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto,
      com.google.protobuf.Empty> getImportOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ImportOrder",
      requestType = org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto,
      com.google.protobuf.Empty> getImportOrderMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto, com.google.protobuf.Empty> getImportOrderMethod;
    if ((getImportOrderMethod = OrderServiceGrpc.getImportOrderMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getImportOrderMethod = OrderServiceGrpc.getImportOrderMethod) == null) {
          OrderServiceGrpc.getImportOrderMethod = getImportOrderMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ImportOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("ImportOrder"))
              .build();
        }
      }
    }
    return getImportOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto,
      org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto> getExportOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExportOrder",
      requestType = org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto,
      org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto> getExportOrderMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto, org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto> getExportOrderMethod;
    if ((getExportOrderMethod = OrderServiceGrpc.getExportOrderMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getExportOrderMethod = OrderServiceGrpc.getExportOrderMethod) == null) {
          OrderServiceGrpc.getExportOrderMethod = getExportOrderMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto, org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExportOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("ExportOrder"))
              .build();
        }
      }
    }
    return getExportOrderMethod;
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
    default void placeOrder(org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPlaceOrderMethod(), responseObserver);
    }

    /**
     */
    default void importOrder(org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getImportOrderMethod(), responseObserver);
    }

    /**
     */
    default void exportOrder(org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExportOrderMethod(), responseObserver);
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
    public void placeOrder(org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPlaceOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void importOrder(org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getImportOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exportOrder(org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExportOrderMethod(), getCallOptions()), request, responseObserver);
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
    public org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto placeOrder(org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPlaceOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty importOrder(org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getImportOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto exportOrder(org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExportOrderMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto> placeOrder(
        org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPlaceOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> importOrder(
        org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getImportOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto> exportOrder(
        org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExportOrderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_ORDER = 0;
  private static final int METHODID_GET_ORDER = 1;
  private static final int METHODID_PLACE_ORDER = 2;
  private static final int METHODID_IMPORT_ORDER = 3;
  private static final int METHODID_EXPORT_ORDER = 4;

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
        case METHODID_PLACE_ORDER:
          serviceImpl.placeOrder((org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto>) responseObserver);
          break;
        case METHODID_IMPORT_ORDER:
          serviceImpl.importOrder((org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_EXPORT_ORDER:
          serviceImpl.exportOrder((org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto>) responseObserver);
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
          getPlaceOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto,
              org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto>(
                service, METHODID_PLACE_ORDER)))
        .addMethod(
          getImportOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto,
              com.google.protobuf.Empty>(
                service, METHODID_IMPORT_ORDER)))
        .addMethod(
          getExportOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto,
              org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto>(
                service, METHODID_EXPORT_ORDER)))
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
              .addMethod(getPlaceOrderMethod())
              .addMethod(getImportOrderMethod())
              .addMethod(getExportOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
