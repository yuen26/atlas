// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: product.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.product;

public interface ProductProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:product.ProductProto)
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
   * <code>double price = 3;</code>
   * @return The price.
   */
  double getPrice();
}
