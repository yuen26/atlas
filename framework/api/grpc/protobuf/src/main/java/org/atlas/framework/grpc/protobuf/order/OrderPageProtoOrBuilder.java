// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: order.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.order;

public interface OrderPageProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:order.OrderPageProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .order.OrderProto order = 1;</code>
   */
  java.util.List<org.atlas.framework.grpc.protobuf.order.OrderProto> 
      getOrderList();
  /**
   * <code>repeated .order.OrderProto order = 1;</code>
   */
  org.atlas.framework.grpc.protobuf.order.OrderProto getOrder(int index);
  /**
   * <code>repeated .order.OrderProto order = 1;</code>
   */
  int getOrderCount();
  /**
   * <code>repeated .order.OrderProto order = 1;</code>
   */
  java.util.List<? extends org.atlas.framework.grpc.protobuf.order.OrderProtoOrBuilder> 
      getOrderOrBuilderList();
  /**
   * <code>repeated .order.OrderProto order = 1;</code>
   */
  org.atlas.framework.grpc.protobuf.order.OrderProtoOrBuilder getOrderOrBuilder(
      int index);

  /**
   * <code>int64 total_count = 2;</code>
   * @return The totalCount.
   */
  long getTotalCount();
}