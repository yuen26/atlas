// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: product.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.product;

public interface UpdateProductRequestProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:product.UpdateProductRequestProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 category_id = 3;</code>
   * @return The categoryId.
   */
  int getCategoryId();

  /**
   * <code>double price = 4;</code>
   * @return The price.
   */
  double getPrice();

  /**
   * <code>int32 quantity = 5;</code>
   * @return The quantity.
   */
  int getQuantity();

  /**
   * <code>string status = 6;</code>
   * @return The status.
   */
  java.lang.String getStatus();
  /**
   * <code>string status = 6;</code>
   * @return The bytes for status.
   */
  com.google.protobuf.ByteString
      getStatusBytes();

  /**
   * <code>bool featured = 7;</code>
   * @return The featured.
   */
  boolean getFeatured();
}