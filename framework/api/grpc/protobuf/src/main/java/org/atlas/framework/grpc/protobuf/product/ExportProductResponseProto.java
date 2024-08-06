// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: product.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.product;

/**
 * Protobuf type {@code product.ExportProductResponseProto}
 */
public final class ExportProductResponseProto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:product.ExportProductResponseProto)
    ExportProductResponseProtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ExportProductResponseProto.newBuilder() to construct.
  private ExportProductResponseProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ExportProductResponseProto() {
    fileContent_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ExportProductResponseProto();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ExportProductResponseProto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ExportProductResponseProto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto.class, org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto.Builder.class);
  }

  public static final int FILE_CONTENT_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString fileContent_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes file_content = 1;</code>
   * @return The fileContent.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getFileContent() {
    return fileContent_;
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
    if (!fileContent_.isEmpty()) {
      output.writeBytes(1, fileContent_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!fileContent_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, fileContent_);
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
    if (!(obj instanceof org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto)) {
      return super.equals(obj);
    }
    org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto other = (org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto) obj;

    if (!getFileContent()
        .equals(other.getFileContent())) return false;
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
    hash = (37 * hash) + FILE_CONTENT_FIELD_NUMBER;
    hash = (53 * hash) + getFileContent().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto parseFrom(
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
  public static Builder newBuilder(org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto prototype) {
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
   * Protobuf type {@code product.ExportProductResponseProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:product.ExportProductResponseProto)
      org.atlas.framework.grpc.protobuf.product.ExportProductResponseProtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ExportProductResponseProto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ExportProductResponseProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto.class, org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto.Builder.class);
    }

    // Construct using org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto.newBuilder()
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
      fileContent_ = com.google.protobuf.ByteString.EMPTY;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.atlas.framework.grpc.protobuf.product.Product.internal_static_product_ExportProductResponseProto_descriptor;
    }

    @java.lang.Override
    public org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto getDefaultInstanceForType() {
      return org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto.getDefaultInstance();
    }

    @java.lang.Override
    public org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto build() {
      org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto buildPartial() {
      org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto result = new org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.fileContent_ = fileContent_;
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
      if (other instanceof org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto) {
        return mergeFrom((org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto other) {
      if (other == org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto.getDefaultInstance()) return this;
      if (other.getFileContent() != com.google.protobuf.ByteString.EMPTY) {
        setFileContent(other.getFileContent());
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
              fileContent_ = input.readBytes();
              bitField0_ |= 0x00000001;
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

    private com.google.protobuf.ByteString fileContent_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes file_content = 1;</code>
     * @return The fileContent.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getFileContent() {
      return fileContent_;
    }
    /**
     * <code>bytes file_content = 1;</code>
     * @param value The fileContent to set.
     * @return This builder for chaining.
     */
    public Builder setFileContent(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      fileContent_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>bytes file_content = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearFileContent() {
      bitField0_ = (bitField0_ & ~0x00000001);
      fileContent_ = getDefaultInstance().getFileContent();
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


    // @@protoc_insertion_point(builder_scope:product.ExportProductResponseProto)
  }

  // @@protoc_insertion_point(class_scope:product.ExportProductResponseProto)
  private static final org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto();
  }

  public static org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ExportProductResponseProto>
      PARSER = new com.google.protobuf.AbstractParser<ExportProductResponseProto>() {
    @java.lang.Override
    public ExportProductResponseProto parsePartialFrom(
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

  public static com.google.protobuf.Parser<ExportProductResponseProto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ExportProductResponseProto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
