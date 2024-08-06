// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: aggregator.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.aggregator;

public final class Aggregator {
  private Aggregator() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_aggregator_OrderProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_aggregator_OrderProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_aggregator_CustomerProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_aggregator_CustomerProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_aggregator_OrderItemProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_aggregator_OrderItemProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_aggregator_GetOrderRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_aggregator_GetOrderRequestProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020aggregator.proto\022\naggregator\032\033google/p" +
      "rotobuf/empty.proto\"\251\001\n\nOrderProto\022\n\n\002id" +
      "\030\001 \001(\005\022+\n\010customer\030\002 \001(\0132\031.aggregator.Cu" +
      "stomerProto\022\016\n\006amount\030\003 \001(\001\022\016\n\006status\030\004 " +
      "\001(\t\022.\n\norder_item\030\005 \003(\0132\032.aggregator.Ord" +
      "erItemProto\022\022\n\ncreated_at\030\006 \001(\t\"<\n\rCusto" +
      "merProto\022\n\n\002id\030\001 \001(\005\022\020\n\010username\030\002 \001(\t\022\r" +
      "\n\005email\030\003 \001(\t\"{\n\016OrderItemProto\022\022\n\nprodu" +
      "ct_id\030\001 \001(\005\022\024\n\014product_name\030\002 \001(\t\022\025\n\rpro" +
      "duct_price\030\003 \001(\001\022\026\n\016product_status\030\004 \001(\t" +
      "\022\020\n\010quantity\030\005 \001(\005\"\"\n\024GetOrderRequestPro" +
      "to\022\n\n\002id\030\001 \001(\0052V\n\014OrderService\022F\n\010GetOrd" +
      "er\022 .aggregator.GetOrderRequestProto\032\026.a" +
      "ggregator.OrderProto\"\000B0\n,org.atlas.fram" +
      "ework.grpc.protobuf.aggregatorP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.EmptyProto.getDescriptor(),
        });
    internal_static_aggregator_OrderProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_aggregator_OrderProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_aggregator_OrderProto_descriptor,
        new java.lang.String[] { "Id", "Customer", "Amount", "Status", "OrderItem", "CreatedAt", });
    internal_static_aggregator_CustomerProto_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_aggregator_CustomerProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_aggregator_CustomerProto_descriptor,
        new java.lang.String[] { "Id", "Username", "Email", });
    internal_static_aggregator_OrderItemProto_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_aggregator_OrderItemProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_aggregator_OrderItemProto_descriptor,
        new java.lang.String[] { "ProductId", "ProductName", "ProductPrice", "ProductStatus", "Quantity", });
    internal_static_aggregator_GetOrderRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_aggregator_GetOrderRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_aggregator_GetOrderRequestProto_descriptor,
        new java.lang.String[] { "Id", });
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}