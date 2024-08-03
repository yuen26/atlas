// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: order.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.order;

public interface OrderProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:order.OrderProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>int32 customer_id = 2;</code>
   * @return The customerId.
   */
  int getCustomerId();

  /**
   * <code>double amount = 3;</code>
   * @return The amount.
   */
  double getAmount();

  /**
   * <code>string status = 4;</code>
   * @return The status.
   */
  java.lang.String getStatus();
  /**
   * <code>string status = 4;</code>
   * @return The bytes for status.
   */
  com.google.protobuf.ByteString
      getStatusBytes();

  /**
   * <code>repeated .order.OrderItemProto order_item = 5;</code>
   */
  java.util.List<org.atlas.framework.grpc.protobuf.order.OrderItemProto> 
      getOrderItemList();
  /**
   * <code>repeated .order.OrderItemProto order_item = 5;</code>
   */
  org.atlas.framework.grpc.protobuf.order.OrderItemProto getOrderItem(int index);
  /**
   * <code>repeated .order.OrderItemProto order_item = 5;</code>
   */
  int getOrderItemCount();
  /**
   * <code>repeated .order.OrderItemProto order_item = 5;</code>
   */
  java.util.List<? extends org.atlas.framework.grpc.protobuf.order.OrderItemProtoOrBuilder> 
      getOrderItemOrBuilderList();
  /**
   * <code>repeated .order.OrderItemProto order_item = 5;</code>
   */
  org.atlas.framework.grpc.protobuf.order.OrderItemProtoOrBuilder getOrderItemOrBuilder(
      int index);

  /**
   * <code>string created_at = 6;</code>
   * @return The createdAt.
   */
  java.lang.String getCreatedAt();
  /**
   * <code>string created_at = 6;</code>
   * @return The bytes for createdAt.
   */
  com.google.protobuf.ByteString
      getCreatedAtBytes();
}
