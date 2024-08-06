// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: product.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.product;

/**
 * Protobuf type {@code product.ListProductByIdsRequestProto}
 */
public final class ListProductByIdsRequestProto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:product.ListProductByIdsRequestProto)
    ListProductByIdsRequestProtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ListProductByIdsRequestProto.newBuilder() to construct.
  private ListProductByIdsRequestProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ListProductByIdsRequestProto() {
    id_ = emptyIntList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ListProductByIdsRequestProto();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ListProductByIdsRequestProto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ListProductByIdsRequestProto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto.class, org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private com.google.protobuf.Internal.IntList id_ =
      emptyIntList();
  /**
   * <code>repeated int32 id = 1;</code>
   * @return A list containing the id.
   */
  @java.lang.Override
  public java.util.List<java.lang.Integer>
      getIdList() {
    return id_;
  }
  /**
   * <code>repeated int32 id = 1;</code>
   * @return The count of id.
   */
  public int getIdCount() {
    return id_.size();
  }
  /**
   * <code>repeated int32 id = 1;</code>
   * @param index The index of the element to return.
   * @return The id at the given index.
   */
  public int getId(int index) {
    return id_.getInt(index);
  }
  private int idMemoizedSerializedSize = -1;

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
    getSerializedSize();
    if (getIdList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(idMemoizedSerializedSize);
    }
    for (int i = 0; i < id_.size(); i++) {
      output.writeInt32NoTag(id_.getInt(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < id_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(id_.getInt(i));
      }
      size += dataSize;
      if (!getIdList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      idMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto)) {
      return super.equals(obj);
    }
    org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto other = (org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto) obj;

    if (!getIdList()
        .equals(other.getIdList())) return false;
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
    if (getIdCount() > 0) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getIdList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto parseFrom(
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
  public static Builder newBuilder(org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto prototype) {
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
   * Protobuf type {@code product.ListProductByIdsRequestProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:product.ListProductByIdsRequestProto)
      org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ListProductByIdsRequestProto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ListProductByIdsRequestProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto.class, org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto.Builder.class);
    }

    // Construct using org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto.newBuilder()
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
      id_ = emptyIntList();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ListProductByIdsRequestProto_descriptor;
    }

    @java.lang.Override
    public org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto getDefaultInstanceForType() {
      return org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto.getDefaultInstance();
    }

    @java.lang.Override
    public org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto build() {
      org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto buildPartial() {
      org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto result = new org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        id_.makeImmutable();
        result.id_ = id_;
      }
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
      if (other instanceof org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto) {
        return mergeFrom((org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto other) {
      if (other == org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto.getDefaultInstance()) return this;
      if (!other.id_.isEmpty()) {
        if (id_.isEmpty()) {
          id_ = other.id_;
          id_.makeImmutable();
          bitField0_ |= 0x00000001;
        } else {
          ensureIdIsMutable();
          id_.addAll(other.id_);
        }
        onChanged();
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
            case 8: {
              int v = input.readInt32();
              ensureIdIsMutable();
              id_.addInt(v);
              break;
            } // case 8
            case 10: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              ensureIdIsMutable();
              while (input.getBytesUntilLimit() > 0) {
                id_.addInt(input.readInt32());
              }
              input.popLimit(limit);
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

    private com.google.protobuf.Internal.IntList id_ = emptyIntList();
    private void ensureIdIsMutable() {
      if (!id_.isModifiable()) {
        id_ = makeMutableCopy(id_);
      }
      bitField0_ |= 0x00000001;
    }
    /**
     * <code>repeated int32 id = 1;</code>
     * @return A list containing the id.
     */
    public java.util.List<java.lang.Integer>
        getIdList() {
      id_.makeImmutable();
      return id_;
    }
    /**
     * <code>repeated int32 id = 1;</code>
     * @return The count of id.
     */
    public int getIdCount() {
      return id_.size();
    }
    /**
     * <code>repeated int32 id = 1;</code>
     * @param index The index of the element to return.
     * @return The id at the given index.
     */
    public int getId(int index) {
      return id_.getInt(index);
    }
    /**
     * <code>repeated int32 id = 1;</code>
     * @param index The index to set the value at.
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(
        int index, int value) {

      ensureIdIsMutable();
      id_.setInt(index, value);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 id = 1;</code>
     * @param value The id to add.
     * @return This builder for chaining.
     */
    public Builder addId(int value) {

      ensureIdIsMutable();
      id_.addInt(value);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 id = 1;</code>
     * @param values The id to add.
     * @return This builder for chaining.
     */
    public Builder addAllId(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureIdIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, id_);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      id_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:product.ListProductByIdsRequestProto)
  }

  // @@protoc_insertion_point(class_scope:product.ListProductByIdsRequestProto)
  private static final org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto();
  }

  public static org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ListProductByIdsRequestProto>
      PARSER = new com.google.protobuf.AbstractParser<ListProductByIdsRequestProto>() {
    @java.lang.Override
    public ListProductByIdsRequestProto parsePartialFrom(
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

  public static com.google.protobuf.Parser<ListProductByIdsRequestProto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ListProductByIdsRequestProto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
