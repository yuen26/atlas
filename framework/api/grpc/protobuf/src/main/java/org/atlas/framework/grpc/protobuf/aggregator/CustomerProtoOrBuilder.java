// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: aggregator.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.aggregator;

public interface CustomerProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:aggregator.CustomerProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string username = 2;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <code>string username = 2;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>string email = 3;</code>
   * @return The email.
   */
  java.lang.String getEmail();
  /**
   * <code>string email = 3;</code>
   * @return The bytes for email.
   */
  com.google.protobuf.ByteString
      getEmailBytes();
}