// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: order.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.order;

public final class Order {
  private Order() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_OrderProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_OrderProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_OrderItemProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_OrderItemProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_OrderPageProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_OrderPageProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_ListOrderRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_ListOrderRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_GetOrderRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_GetOrderRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_GetOrderStatusRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_GetOrderStatusRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_OrderStatusProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_OrderStatusProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_CreateOrderRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_CreateOrderRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_CreateOrderResponseProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_CreateOrderResponseProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013order.proto\022\005order\032\033google/protobuf/em" +
      "pty.proto\"\214\001\n\nOrderProto\022\n\n\002id\030\001 \001(\005\022\023\n\013" +
      "customer_id\030\002 \001(\005\022\016\n\006amount\030\003 \001(\001\022\016\n\006sta" +
      "tus\030\004 \001(\t\022)\n\norder_item\030\005 \003(\0132\025.order.Or" +
      "derItemProto\022\022\n\ncreated_at\030\006 \001(\t\"M\n\016Orde" +
      "rItemProto\022\022\n\nproduct_id\030\001 \001(\005\022\025\n\rproduc" +
      "t_price\030\002 \001(\001\022\020\n\010quantity\030\003 \001(\005\"G\n\016Order" +
      "PageProto\022 \n\005order\030\001 \003(\0132\021.order.OrderPr" +
      "oto\022\023\n\013total_count\030\002 \001(\003\"3\n\025ListOrderReq" +
      "uestProto\022\014\n\004page\030\001 \001(\005\022\014\n\004size\030\002 \001(\005\"\"\n" +
      "\024GetOrderRequestProto\022\n\n\002id\030\001 \001(\005\"(\n\032Get" +
      "OrderStatusRequestProto\022\n\n\002id\030\001 \001(\005\"\"\n\020O" +
      "rderStatusProto\022\016\n\006status\030\001 \001(\t\"D\n\027Creat" +
      "eOrderRequestProto\022)\n\norder_item\030\001 \003(\0132\025" +
      ".order.OrderItemProto\"&\n\030CreateOrderResp" +
      "onseProto\022\n\n\002id\030\001 \001(\0052\251\002\n\014OrderService\022B" +
      "\n\tListOrder\022\034.order.ListOrderRequestProt" +
      "o\032\025.order.OrderPageProto\"\000\022<\n\010GetOrder\022\033" +
      ".order.GetOrderRequestProto\032\021.order.Orde" +
      "rProto\"\000\022N\n\016GetOrderStatus\022!.order.GetOr" +
      "derStatusRequestProto\032\027.order.OrderStatu" +
      "sProto\"\000\022G\n\013CreateOrder\022\036.order.CreateOr" +
      "derRequestProto\032\026.google.protobuf.Empty\"" +
      "\000B+\n\'org.atlas.framework.grpc.protobuf.o" +
      "rderP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.EmptyProto.getDescriptor(),
        });
    internal_static_order_OrderProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_order_OrderProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_OrderProto_descriptor,
        new java.lang.String[] { "Id", "CustomerId", "Amount", "Status", "OrderItem", "CreatedAt", });
    internal_static_order_OrderItemProto_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_order_OrderItemProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_OrderItemProto_descriptor,
        new java.lang.String[] { "ProductId", "ProductPrice", "Quantity", });
    internal_static_order_OrderPageProto_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_order_OrderPageProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_OrderPageProto_descriptor,
        new java.lang.String[] { "Order", "TotalCount", });
    internal_static_order_ListOrderRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_order_ListOrderRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_ListOrderRequestProto_descriptor,
        new java.lang.String[] { "Page", "Size", });
    internal_static_order_GetOrderRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_order_GetOrderRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_GetOrderRequestProto_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_order_GetOrderStatusRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_order_GetOrderStatusRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_GetOrderStatusRequestProto_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_order_OrderStatusProto_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_order_OrderStatusProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_OrderStatusProto_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_order_CreateOrderRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_order_CreateOrderRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_CreateOrderRequestProto_descriptor,
        new java.lang.String[] { "OrderItem", });
    internal_static_order_CreateOrderResponseProto_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_order_CreateOrderResponseProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_CreateOrderResponseProto_descriptor,
        new java.lang.String[] { "Id", });
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}