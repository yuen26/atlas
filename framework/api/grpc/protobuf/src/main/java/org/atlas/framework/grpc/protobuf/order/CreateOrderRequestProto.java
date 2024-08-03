// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: order.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.order;

/**
 * Protobuf type {@code order.CreateOrderRequestProto}
 */
public final class CreateOrderRequestProto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:order.CreateOrderRequestProto)
    CreateOrderRequestProtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CreateOrderRequestProto.newBuilder() to construct.
  private CreateOrderRequestProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CreateOrderRequestProto() {
    orderItem_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CreateOrderRequestProto();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.atlas.framework.grpc.protobuf.order.Order.internal_static_order_CreateOrderRequestProto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.atlas.framework.grpc.protobuf.order.Order.internal_static_order_CreateOrderRequestProto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto.class, org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto.Builder.class);
  }

  public static final int ORDER_ITEM_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private java.util.List<org.atlas.framework.grpc.protobuf.order.OrderItemProto> orderItem_;
  /**
   * <code>repeated .order.OrderItemProto order_item = 1;</code>
   */
  @java.lang.Override
  public java.util.List<org.atlas.framework.grpc.protobuf.order.OrderItemProto> getOrderItemList() {
    return orderItem_;
  }
  /**
   * <code>repeated .order.OrderItemProto order_item = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends org.atlas.framework.grpc.protobuf.order.OrderItemProtoOrBuilder> 
      getOrderItemOrBuilderList() {
    return orderItem_;
  }
  /**
   * <code>repeated .order.OrderItemProto order_item = 1;</code>
   */
  @java.lang.Override
  public int getOrderItemCount() {
    return orderItem_.size();
  }
  /**
   * <code>repeated .order.OrderItemProto order_item = 1;</code>
   */
  @java.lang.Override
  public org.atlas.framework.grpc.protobuf.order.OrderItemProto getOrderItem(int index) {
    return orderItem_.get(index);
  }
  /**
   * <code>repeated .order.OrderItemProto order_item = 1;</code>
   */
  @java.lang.Override
  public org.atlas.framework.grpc.protobuf.order.OrderItemProtoOrBuilder getOrderItemOrBuilder(
      int index) {
    return orderItem_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < orderItem_.size(); i++) {
      output.writeMessage(1, orderItem_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < orderItem_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, orderItem_.get(i));
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto)) {
      return super.equals(obj);
    }
    org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto other = (org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto) obj;

    if (!getOrderItemList()
        .equals(other.getOrderItemList())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getOrderItemCount() > 0) {
      hash = (37 * hash) + ORDER_ITEM_FIELD_NUMBER;
      hash = (53 * hash) + getOrderItemList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code order.CreateOrderRequestProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:order.CreateOrderRequestProto)
      org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.atlas.framework.grpc.protobuf.order.Order.internal_static_order_CreateOrderRequestProto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.atlas.framework.grpc.protobuf.order.Order.internal_static_order_CreateOrderRequestProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto.class, org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto.Builder.class);
    }

    // Construct using org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      if (orderItemBuilder_ == null) {
        orderItem_ = java.util.Collections.emptyList();
      } else {
        orderItem_ = null;
        orderItemBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.atlas.framework.grpc.protobuf.order.Order.internal_static_order_CreateOrderRequestProto_descriptor;
    }

    @java.lang.Override
    public org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto getDefaultInstanceForType() {
      return org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto.getDefaultInstance();
    }

    @java.lang.Override
    public org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto build() {
      org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto buildPartial() {
      org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto result = new org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto result) {
      if (orderItemBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          orderItem_ = java.util.Collections.unmodifiableList(orderItem_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.orderItem_ = orderItem_;
      } else {
        result.orderItem_ = orderItemBuilder_.build();
      }
    }

    private void buildPartial0(org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto result) {
      int from_bitField0_ = bitField0_;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto) {
        return mergeFrom((org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto other) {
      if (other == org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto.getDefaultInstance()) return this;
      if (orderItemBuilder_ == null) {
        if (!other.orderItem_.isEmpty()) {
          if (orderItem_.isEmpty()) {
            orderItem_ = other.orderItem_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureOrderItemIsMutable();
            orderItem_.addAll(other.orderItem_);
          }
          onChanged();
        }
      } else {
        if (!other.orderItem_.isEmpty()) {
          if (orderItemBuilder_.isEmpty()) {
            orderItemBuilder_.dispose();
            orderItemBuilder_ = null;
            orderItem_ = other.orderItem_;
            bitField0_ = (bitField0_ & ~0x00000001);
            orderItemBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getOrderItemFieldBuilder() : null;
          } else {
            orderItemBuilder_.addAllMessages(other.orderItem_);
          }
        }
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              org.atlas.framework.grpc.protobuf.order.OrderItemProto m =
                  input.readMessage(
                      org.atlas.framework.grpc.protobuf.order.OrderItemProto.parser(),
                      extensionRegistry);
              if (orderItemBuilder_ == null) {
                ensureOrderItemIsMutable();
                orderItem_.add(m);
              } else {
                orderItemBuilder_.addMessage(m);
              }
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.util.List<org.atlas.framework.grpc.protobuf.order.OrderItemProto> orderItem_ =
      java.util.Collections.emptyList();
    private void ensureOrderItemIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        orderItem_ = new java.util.ArrayList<org.atlas.framework.grpc.protobuf.order.OrderItemProto>(orderItem_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.atlas.framework.grpc.protobuf.order.OrderItemProto, org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder, org.atlas.framework.grpc.protobuf.order.OrderItemProtoOrBuilder> orderItemBuilder_;

    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public java.util.List<org.atlas.framework.grpc.protobuf.order.OrderItemProto> getOrderItemList() {
      if (orderItemBuilder_ == null) {
        return java.util.Collections.unmodifiableList(orderItem_);
      } else {
        return orderItemBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public int getOrderItemCount() {
      if (orderItemBuilder_ == null) {
        return orderItem_.size();
      } else {
        return orderItemBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public org.atlas.framework.grpc.protobuf.order.OrderItemProto getOrderItem(int index) {
      if (orderItemBuilder_ == null) {
        return orderItem_.get(index);
      } else {
        return orderItemBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public Builder setOrderItem(
        int index, org.atlas.framework.grpc.protobuf.order.OrderItemProto value) {
      if (orderItemBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOrderItemIsMutable();
        orderItem_.set(index, value);
        onChanged();
      } else {
        orderItemBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public Builder setOrderItem(
        int index, org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder builderForValue) {
      if (orderItemBuilder_ == null) {
        ensureOrderItemIsMutable();
        orderItem_.set(index, builderForValue.build());
        onChanged();
      } else {
        orderItemBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public Builder addOrderItem(org.atlas.framework.grpc.protobuf.order.OrderItemProto value) {
      if (orderItemBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOrderItemIsMutable();
        orderItem_.add(value);
        onChanged();
      } else {
        orderItemBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public Builder addOrderItem(
        int index, org.atlas.framework.grpc.protobuf.order.OrderItemProto value) {
      if (orderItemBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOrderItemIsMutable();
        orderItem_.add(index, value);
        onChanged();
      } else {
        orderItemBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public Builder addOrderItem(
        org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder builderForValue) {
      if (orderItemBuilder_ == null) {
        ensureOrderItemIsMutable();
        orderItem_.add(builderForValue.build());
        onChanged();
      } else {
        orderItemBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public Builder addOrderItem(
        int index, org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder builderForValue) {
      if (orderItemBuilder_ == null) {
        ensureOrderItemIsMutable();
        orderItem_.add(index, builderForValue.build());
        onChanged();
      } else {
        orderItemBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public Builder addAllOrderItem(
        java.lang.Iterable<? extends org.atlas.framework.grpc.protobuf.order.OrderItemProto> values) {
      if (orderItemBuilder_ == null) {
        ensureOrderItemIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, orderItem_);
        onChanged();
      } else {
        orderItemBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public Builder clearOrderItem() {
      if (orderItemBuilder_ == null) {
        orderItem_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        orderItemBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public Builder removeOrderItem(int index) {
      if (orderItemBuilder_ == null) {
        ensureOrderItemIsMutable();
        orderItem_.remove(index);
        onChanged();
      } else {
        orderItemBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder getOrderItemBuilder(
        int index) {
      return getOrderItemFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public org.atlas.framework.grpc.protobuf.order.OrderItemProtoOrBuilder getOrderItemOrBuilder(
        int index) {
      if (orderItemBuilder_ == null) {
        return orderItem_.get(index);  } else {
        return orderItemBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public java.util.List<? extends org.atlas.framework.grpc.protobuf.order.OrderItemProtoOrBuilder> 
         getOrderItemOrBuilderList() {
      if (orderItemBuilder_ != null) {
        return orderItemBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(orderItem_);
      }
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder addOrderItemBuilder() {
      return getOrderItemFieldBuilder().addBuilder(
          org.atlas.framework.grpc.protobuf.order.OrderItemProto.getDefaultInstance());
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder addOrderItemBuilder(
        int index) {
      return getOrderItemFieldBuilder().addBuilder(
          index, org.atlas.framework.grpc.protobuf.order.OrderItemProto.getDefaultInstance());
    }
    /**
     * <code>repeated .order.OrderItemProto order_item = 1;</code>
     */
    public java.util.List<org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder> 
         getOrderItemBuilderList() {
      return getOrderItemFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.atlas.framework.grpc.protobuf.order.OrderItemProto, org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder, org.atlas.framework.grpc.protobuf.order.OrderItemProtoOrBuilder> 
        getOrderItemFieldBuilder() {
      if (orderItemBuilder_ == null) {
        orderItemBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            org.atlas.framework.grpc.protobuf.order.OrderItemProto, org.atlas.framework.grpc.protobuf.order.OrderItemProto.Builder, org.atlas.framework.grpc.protobuf.order.OrderItemProtoOrBuilder>(
                orderItem_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        orderItem_ = null;
      }
      return orderItemBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:order.CreateOrderRequestProto)
  }

  // @@protoc_insertion_point(class_scope:order.CreateOrderRequestProto)
  private static final org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto();
  }

  public static org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CreateOrderRequestProto>
      PARSER = new com.google.protobuf.AbstractParser<CreateOrderRequestProto>() {
    @java.lang.Override
    public CreateOrderRequestProto parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<CreateOrderRequestProto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CreateOrderRequestProto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

