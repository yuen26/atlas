package org.atlas.framework.grpc.protobuf.product;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: product.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProductServiceGrpc {

  private ProductServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "product.ProductService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ListProductRequestProto,
      org.atlas.framework.grpc.protobuf.product.ProductPageProto> getListProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListProduct",
      requestType = org.atlas.framework.grpc.protobuf.product.ListProductRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.product.ProductPageProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ListProductRequestProto,
      org.atlas.framework.grpc.protobuf.product.ProductPageProto> getListProductMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ListProductRequestProto, org.atlas.framework.grpc.protobuf.product.ProductPageProto> getListProductMethod;
    if ((getListProductMethod = ProductServiceGrpc.getListProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getListProductMethod = ProductServiceGrpc.getListProductMethod) == null) {
          ProductServiceGrpc.getListProductMethod = getListProductMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.ListProductRequestProto, org.atlas.framework.grpc.protobuf.product.ProductPageProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.ListProductRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.ProductPageProto.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("ListProduct"))
              .build();
        }
      }
    }
    return getListProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto,
      org.atlas.framework.grpc.protobuf.product.ProductListProto> getListProductByIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListProductByIds",
      requestType = org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.product.ProductListProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto,
      org.atlas.framework.grpc.protobuf.product.ProductListProto> getListProductByIdsMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto, org.atlas.framework.grpc.protobuf.product.ProductListProto> getListProductByIdsMethod;
    if ((getListProductByIdsMethod = ProductServiceGrpc.getListProductByIdsMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getListProductByIdsMethod = ProductServiceGrpc.getListProductByIdsMethod) == null) {
          ProductServiceGrpc.getListProductByIdsMethod = getListProductByIdsMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto, org.atlas.framework.grpc.protobuf.product.ProductListProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListProductByIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.ProductListProto.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("ListProductByIds"))
              .build();
        }
      }
    }
    return getListProductByIdsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.GetProductRequestProto,
      org.atlas.framework.grpc.protobuf.product.ProductProto> getGetProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetProduct",
      requestType = org.atlas.framework.grpc.protobuf.product.GetProductRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.product.ProductProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.GetProductRequestProto,
      org.atlas.framework.grpc.protobuf.product.ProductProto> getGetProductMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.GetProductRequestProto, org.atlas.framework.grpc.protobuf.product.ProductProto> getGetProductMethod;
    if ((getGetProductMethod = ProductServiceGrpc.getGetProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getGetProductMethod = ProductServiceGrpc.getGetProductMethod) == null) {
          ProductServiceGrpc.getGetProductMethod = getGetProductMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.GetProductRequestProto, org.atlas.framework.grpc.protobuf.product.ProductProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.GetProductRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.ProductProto.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("GetProduct"))
              .build();
        }
      }
    }
    return getGetProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto,
      org.atlas.framework.grpc.protobuf.product.ProductPageProto> getSearchProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SearchProduct",
      requestType = org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.product.ProductPageProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto,
      org.atlas.framework.grpc.protobuf.product.ProductPageProto> getSearchProductMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto, org.atlas.framework.grpc.protobuf.product.ProductPageProto> getSearchProductMethod;
    if ((getSearchProductMethod = ProductServiceGrpc.getSearchProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getSearchProductMethod = ProductServiceGrpc.getSearchProductMethod) == null) {
          ProductServiceGrpc.getSearchProductMethod = getSearchProductMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto, org.atlas.framework.grpc.protobuf.product.ProductPageProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SearchProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.ProductPageProto.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("SearchProduct"))
              .build();
        }
      }
    }
    return getSearchProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto,
      com.google.protobuf.Empty> getCreateProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateProduct",
      requestType = org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto,
      com.google.protobuf.Empty> getCreateProductMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto, com.google.protobuf.Empty> getCreateProductMethod;
    if ((getCreateProductMethod = ProductServiceGrpc.getCreateProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getCreateProductMethod = ProductServiceGrpc.getCreateProductMethod) == null) {
          ProductServiceGrpc.getCreateProductMethod = getCreateProductMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("CreateProduct"))
              .build();
        }
      }
    }
    return getCreateProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto,
      com.google.protobuf.Empty> getUpdateProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateProduct",
      requestType = org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto,
      com.google.protobuf.Empty> getUpdateProductMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto, com.google.protobuf.Empty> getUpdateProductMethod;
    if ((getUpdateProductMethod = ProductServiceGrpc.getUpdateProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getUpdateProductMethod = ProductServiceGrpc.getUpdateProductMethod) == null) {
          ProductServiceGrpc.getUpdateProductMethod = getUpdateProductMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("UpdateProduct"))
              .build();
        }
      }
    }
    return getUpdateProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto,
      com.google.protobuf.Empty> getDeleteProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteProduct",
      requestType = org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto,
      com.google.protobuf.Empty> getDeleteProductMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto, com.google.protobuf.Empty> getDeleteProductMethod;
    if ((getDeleteProductMethod = ProductServiceGrpc.getDeleteProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getDeleteProductMethod = ProductServiceGrpc.getDeleteProductMethod) == null) {
          ProductServiceGrpc.getDeleteProductMethod = getDeleteProductMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("DeleteProduct"))
              .build();
        }
      }
    }
    return getDeleteProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto,
      com.google.protobuf.Empty> getImportProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ImportProduct",
      requestType = org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto,
      com.google.protobuf.Empty> getImportProductMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto, com.google.protobuf.Empty> getImportProductMethod;
    if ((getImportProductMethod = ProductServiceGrpc.getImportProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getImportProductMethod = ProductServiceGrpc.getImportProductMethod) == null) {
          ProductServiceGrpc.getImportProductMethod = getImportProductMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ImportProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("ImportProduct"))
              .build();
        }
      }
    }
    return getImportProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto,
      org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto> getExportProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExportProduct",
      requestType = org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto.class,
      responseType = org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto,
      org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto> getExportProductMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto, org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto> getExportProductMethod;
    if ((getExportProductMethod = ProductServiceGrpc.getExportProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getExportProductMethod = ProductServiceGrpc.getExportProductMethod) == null) {
          ProductServiceGrpc.getExportProductMethod = getExportProductMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto, org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExportProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("ExportProduct"))
              .build();
        }
      }
    }
    return getExportProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto,
      com.google.protobuf.Empty> getUploadImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadImage",
      requestType = org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto,
      com.google.protobuf.Empty> getUploadImageMethod() {
    io.grpc.MethodDescriptor<org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto, com.google.protobuf.Empty> getUploadImageMethod;
    if ((getUploadImageMethod = ProductServiceGrpc.getUploadImageMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getUploadImageMethod = ProductServiceGrpc.getUploadImageMethod) == null) {
          ProductServiceGrpc.getUploadImageMethod = getUploadImageMethod =
              io.grpc.MethodDescriptor.<org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("UploadImage"))
              .build();
        }
      }
    }
    return getUploadImageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProductServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductServiceStub>() {
        @java.lang.Override
        public ProductServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductServiceStub(channel, callOptions);
        }
      };
    return ProductServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProductServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductServiceBlockingStub>() {
        @java.lang.Override
        public ProductServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductServiceBlockingStub(channel, callOptions);
        }
      };
    return ProductServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProductServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductServiceFutureStub>() {
        @java.lang.Override
        public ProductServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductServiceFutureStub(channel, callOptions);
        }
      };
    return ProductServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void listProduct(org.atlas.framework.grpc.protobuf.product.ListProductRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductPageProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListProductMethod(), responseObserver);
    }

    /**
     */
    default void listProductByIds(org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductListProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListProductByIdsMethod(), responseObserver);
    }

    /**
     */
    default void getProduct(org.atlas.framework.grpc.protobuf.product.GetProductRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetProductMethod(), responseObserver);
    }

    /**
     */
    default void searchProduct(org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductPageProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchProductMethod(), responseObserver);
    }

    /**
     */
    default void createProduct(org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateProductMethod(), responseObserver);
    }

    /**
     */
    default void updateProduct(org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateProductMethod(), responseObserver);
    }

    /**
     */
    default void deleteProduct(org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteProductMethod(), responseObserver);
    }

    /**
     */
    default void importProduct(org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getImportProductMethod(), responseObserver);
    }

    /**
     */
    default void exportProduct(org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExportProductMethod(), responseObserver);
    }

    /**
     */
    default void uploadImage(org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUploadImageMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ProductService.
   */
  public static abstract class ProductServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProductServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ProductService.
   */
  public static final class ProductServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ProductServiceStub> {
    private ProductServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductServiceStub(channel, callOptions);
    }

    /**
     */
    public void listProduct(org.atlas.framework.grpc.protobuf.product.ListProductRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductPageProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listProductByIds(org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductListProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListProductByIdsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProduct(org.atlas.framework.grpc.protobuf.product.GetProductRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchProduct(org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductPageProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createProduct(org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateProduct(org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteProduct(org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void importProduct(org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getImportProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exportProduct(org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto request,
        io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExportProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void uploadImage(org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUploadImageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ProductService.
   */
  public static final class ProductServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProductServiceBlockingStub> {
    private ProductServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.product.ProductPageProto listProduct(org.atlas.framework.grpc.protobuf.product.ListProductRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.product.ProductListProto listProductByIds(org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListProductByIdsMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.product.ProductProto getProduct(org.atlas.framework.grpc.protobuf.product.GetProductRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.product.ProductPageProto searchProduct(org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty createProduct(org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty updateProduct(org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deleteProduct(org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty importProduct(org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getImportProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto exportProduct(org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExportProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty uploadImage(org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUploadImageMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ProductService.
   */
  public static final class ProductServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProductServiceFutureStub> {
    private ProductServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.product.ProductPageProto> listProduct(
        org.atlas.framework.grpc.protobuf.product.ListProductRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.product.ProductListProto> listProductByIds(
        org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListProductByIdsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.product.ProductProto> getProduct(
        org.atlas.framework.grpc.protobuf.product.GetProductRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.product.ProductPageProto> searchProduct(
        org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> createProduct(
        org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateProduct(
        org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteProduct(
        org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> importProduct(
        org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getImportProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto> exportProduct(
        org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExportProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> uploadImage(
        org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUploadImageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_PRODUCT = 0;
  private static final int METHODID_LIST_PRODUCT_BY_IDS = 1;
  private static final int METHODID_GET_PRODUCT = 2;
  private static final int METHODID_SEARCH_PRODUCT = 3;
  private static final int METHODID_CREATE_PRODUCT = 4;
  private static final int METHODID_UPDATE_PRODUCT = 5;
  private static final int METHODID_DELETE_PRODUCT = 6;
  private static final int METHODID_IMPORT_PRODUCT = 7;
  private static final int METHODID_EXPORT_PRODUCT = 8;
  private static final int METHODID_UPLOAD_IMAGE = 9;

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
        case METHODID_LIST_PRODUCT:
          serviceImpl.listProduct((org.atlas.framework.grpc.protobuf.product.ListProductRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductPageProto>) responseObserver);
          break;
        case METHODID_LIST_PRODUCT_BY_IDS:
          serviceImpl.listProductByIds((org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductListProto>) responseObserver);
          break;
        case METHODID_GET_PRODUCT:
          serviceImpl.getProduct((org.atlas.framework.grpc.protobuf.product.GetProductRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductProto>) responseObserver);
          break;
        case METHODID_SEARCH_PRODUCT:
          serviceImpl.searchProduct((org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ProductPageProto>) responseObserver);
          break;
        case METHODID_CREATE_PRODUCT:
          serviceImpl.createProduct((org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_PRODUCT:
          serviceImpl.updateProduct((org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_DELETE_PRODUCT:
          serviceImpl.deleteProduct((org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_IMPORT_PRODUCT:
          serviceImpl.importProduct((org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_EXPORT_PRODUCT:
          serviceImpl.exportProduct((org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto) request,
              (io.grpc.stub.StreamObserver<org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto>) responseObserver);
          break;
        case METHODID_UPLOAD_IMAGE:
          serviceImpl.uploadImage((org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto) request,
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
          getListProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.ListProductRequestProto,
              org.atlas.framework.grpc.protobuf.product.ProductPageProto>(
                service, METHODID_LIST_PRODUCT)))
        .addMethod(
          getListProductByIdsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto,
              org.atlas.framework.grpc.protobuf.product.ProductListProto>(
                service, METHODID_LIST_PRODUCT_BY_IDS)))
        .addMethod(
          getGetProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.GetProductRequestProto,
              org.atlas.framework.grpc.protobuf.product.ProductProto>(
                service, METHODID_GET_PRODUCT)))
        .addMethod(
          getSearchProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto,
              org.atlas.framework.grpc.protobuf.product.ProductPageProto>(
                service, METHODID_SEARCH_PRODUCT)))
        .addMethod(
          getCreateProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto,
              com.google.protobuf.Empty>(
                service, METHODID_CREATE_PRODUCT)))
        .addMethod(
          getUpdateProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto,
              com.google.protobuf.Empty>(
                service, METHODID_UPDATE_PRODUCT)))
        .addMethod(
          getDeleteProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_PRODUCT)))
        .addMethod(
          getImportProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto,
              com.google.protobuf.Empty>(
                service, METHODID_IMPORT_PRODUCT)))
        .addMethod(
          getExportProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto,
              org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto>(
                service, METHODID_EXPORT_PRODUCT)))
        .addMethod(
          getUploadImageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto,
              com.google.protobuf.Empty>(
                service, METHODID_UPLOAD_IMAGE)))
        .build();
  }

  private static abstract class ProductServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProductServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.atlas.framework.grpc.protobuf.product.Product.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProductService");
    }
  }

  private static final class ProductServiceFileDescriptorSupplier
      extends ProductServiceBaseDescriptorSupplier {
    ProductServiceFileDescriptorSupplier() {}
  }

  private static final class ProductServiceMethodDescriptorSupplier
      extends ProductServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProductServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ProductServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProductServiceFileDescriptorSupplier())
              .addMethod(getListProductMethod())
              .addMethod(getListProductByIdsMethod())
              .addMethod(getGetProductMethod())
              .addMethod(getSearchProductMethod())
              .addMethod(getCreateProductMethod())
              .addMethod(getUpdateProductMethod())
              .addMethod(getDeleteProductMethod())
              .addMethod(getImportProductMethod())
              .addMethod(getExportProductMethod())
              .addMethod(getUploadImageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
