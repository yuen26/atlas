// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: product.proto

// Protobuf Java Version: 3.25.1
package org.atlas.framework.grpc.protobuf.product;

public final class Product {
  private Product() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_ProductProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_ProductProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_ProductListProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_ProductListProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_ProductPageProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_ProductPageProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_CategoryProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_CategoryProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_CategoryListProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_CategoryListProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_ListProductRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_ListProductRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_ListProductByIdsRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_ListProductByIdsRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_GetProductRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_GetProductRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_SearchProductRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_SearchProductRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_CreateProductRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_CreateProductRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_CreateProductResponseProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_CreateProductResponseProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_UpdateProductRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_UpdateProductRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_DeleteProductRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_DeleteProductRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_ImportProductRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_ImportProductRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_UploadImageRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_UploadImageRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_ExportProductRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_ExportProductRequestProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_ExportProductResponseProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_ExportProductResponseProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rproduct.proto\022\007product\032\033google/protobu" +
      "f/empty.proto\"\275\001\n\014ProductProto\022\n\n\002id\030\001 \001" +
      "(\005\022\014\n\004name\030\002 \001(\t\022(\n\010category\030\003 \001(\0132\026.pro" +
      "duct.CategoryProto\022\r\n\005price\030\004 \001(\001\022\020\n\010qua" +
      "ntity\030\005 \001(\005\022\016\n\006status\030\006 \001(\t\022\020\n\010featured\030" +
      "\007 \001(\010\022\022\n\ncreated_at\030\010 \001(\t\022\022\n\nupdated_at\030" +
      "\t \001(\t\":\n\020ProductListProto\022&\n\007product\030\001 \003" +
      "(\0132\025.product.ProductProto\"O\n\020ProductPage" +
      "Proto\022&\n\007product\030\001 \003(\0132\025.product.Product" +
      "Proto\022\023\n\013total_count\030\002 \001(\003\")\n\rCategoryPr" +
      "oto\022\n\n\002id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\"=\n\021Categor" +
      "yListProto\022(\n\010category\030\001 \003(\0132\026.product.C" +
      "ategoryProto\"\376\001\n\027ListProductRequestProto" +
      "\022\n\n\002id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\022\023\n\013category_i" +
      "d\030\003 \001(\005\022\021\n\tmin_price\030\004 \001(\001\022\021\n\tmax_price\030" +
      "\005 \001(\001\022\020\n\010in_stock\030\006 \001(\010\022\016\n\006status\030\007 \001(\t\022" +
      "\020\n\010featured\030\010 \001(\010\022\030\n\020start_created_at\030\t " +
      "\001(\t\022\026\n\016end_created_at\030\n \001(\t\022\014\n\004page\030\013 \001(" +
      "\005\022\014\n\004size\030\014 \001(\005\022\014\n\004sort\030\r \001(\t\"*\n\034ListPro" +
      "ductByIdsRequestProto\022\n\n\002id\030\001 \003(\005\"$\n\026Get" +
      "ProductRequestProto\022\n\n\002id\030\001 \001(\005\"\200\002\n\031Sear" +
      "chProductRequestProto\022\n\n\002id\030\001 \001(\005\022\014\n\004nam" +
      "e\030\002 \001(\t\022\023\n\013category_id\030\003 \001(\005\022\021\n\tmin_pric" +
      "e\030\004 \001(\001\022\021\n\tmax_price\030\005 \001(\001\022\020\n\010in_stock\030\006" +
      " \001(\010\022\016\n\006status\030\007 \001(\t\022\020\n\010featured\030\010 \001(\010\022\030" +
      "\n\020start_created_at\030\t \001(\t\022\026\n\016end_created_" +
      "at\030\n \001(\t\022\014\n\004page\030\013 \001(\005\022\014\n\004size\030\014 \001(\005\022\014\n\004" +
      "sort\030\r \001(\t\"\201\001\n\031CreateProductRequestProto" +
      "\022\014\n\004name\030\001 \001(\t\022\023\n\013category_id\030\002 \001(\005\022\r\n\005p" +
      "rice\030\003 \001(\001\022\020\n\010quantity\030\004 \001(\005\022\016\n\006status\030\005" +
      " \001(\t\022\020\n\010featured\030\006 \001(\010\"(\n\032CreateProductR" +
      "esponseProto\022\n\n\002id\030\001 \001(\005\"\215\001\n\031UpdateProdu" +
      "ctRequestProto\022\n\n\002id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t" +
      "\022\023\n\013category_id\030\003 \001(\005\022\r\n\005price\030\004 \001(\001\022\020\n\010" +
      "quantity\030\005 \001(\005\022\016\n\006status\030\006 \001(\t\022\020\n\010featur" +
      "ed\030\007 \001(\010\"\'\n\031DeleteProductRequestProto\022\n\n" +
      "\002id\030\001 \001(\005\"D\n\031ImportProductRequestProto\022\021" +
      "\n\tfile_type\030\001 \001(\t\022\024\n\014file_content\030\002 \001(\014\"" +
      ";\n\027UploadImageRequestProto\022\n\n\002id\030\001 \001(\005\022\024" +
      "\n\014file_content\030\002 \001(\014\"\367\001\n\031ExportProductRe" +
      "questProto\022\n\n\002id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\022\023\n\013" +
      "category_id\030\003 \001(\005\022\021\n\tmin_price\030\004 \001(\001\022\021\n\t" +
      "max_price\030\005 \001(\001\022\020\n\010in_stock\030\006 \001(\010\022\016\n\006sta" +
      "tus\030\007 \001(\t\022\020\n\010featured\030\010 \001(\010\022\030\n\020start_cre" +
      "ated_at\030\t \001(\t\022\026\n\016end_created_at\030\n \001(\t\022\014\n" +
      "\004sort\030\013 \001(\t\022\021\n\tfile_type\030\014 \001(\t\"2\n\032Export" +
      "ProductResponseProto\022\024\n\014file_content\030\001 \001" +
      "(\0142\263\006\n\016ProductService\022L\n\013ListProduct\022 .p" +
      "roduct.ListProductRequestProto\032\031.product" +
      ".ProductPageProto\"\000\022V\n\020ListProductByIds\022" +
      "%.product.ListProductByIdsRequestProto\032\031" +
      ".product.ProductListProto\"\000\022F\n\nGetProduc" +
      "t\022\037.product.GetProductRequestProto\032\025.pro" +
      "duct.ProductProto\"\000\022P\n\rSearchProduct\022\".p" +
      "roduct.SearchProductRequestProto\032\031.produ" +
      "ct.ProductPageProto\"\000\022M\n\rCreateProduct\022\"" +
      ".product.CreateProductRequestProto\032\026.goo" +
      "gle.protobuf.Empty\"\000\022M\n\rUpdateProduct\022\"." +
      "product.UpdateProductRequestProto\032\026.goog" +
      "le.protobuf.Empty\"\000\022M\n\rDeleteProduct\022\".p" +
      "roduct.DeleteProductRequestProto\032\026.googl" +
      "e.protobuf.Empty\"\000\022M\n\rImportProduct\022\".pr" +
      "oduct.ImportProductRequestProto\032\026.google" +
      ".protobuf.Empty\"\000\022Z\n\rExportProduct\022\".pro" +
      "duct.ExportProductRequestProto\032#.product" +
      ".ExportProductResponseProto\"\000\022I\n\013UploadI" +
      "mage\022 .product.UploadImageRequestProto\032\026" +
      ".google.protobuf.Empty\"\0002W\n\017CategoryServ" +
      "ice\022D\n\014ListCategory\022\026.google.protobuf.Em" +
      "pty\032\032.product.CategoryListProto\"\000B-\n)org" +
      ".atlas.framework.grpc.protobuf.productP\001" +
      "b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.EmptyProto.getDescriptor(),
        });
    internal_static_product_ProductProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_product_ProductProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_ProductProto_descriptor,
        new java.lang.String[] { "Id", "Name", "Category", "Price", "Quantity", "Status", "Featured", "CreatedAt", "UpdatedAt", });
    internal_static_product_ProductListProto_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_product_ProductListProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_ProductListProto_descriptor,
        new java.lang.String[] { "Product", });
    internal_static_product_ProductPageProto_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_product_ProductPageProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_ProductPageProto_descriptor,
        new java.lang.String[] { "Product", "TotalCount", });
    internal_static_product_CategoryProto_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_product_CategoryProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_CategoryProto_descriptor,
        new java.lang.String[] { "Id", "Name", });
    internal_static_product_CategoryListProto_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_product_CategoryListProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_CategoryListProto_descriptor,
        new java.lang.String[] { "Category", });
    internal_static_product_ListProductRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_product_ListProductRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_ListProductRequestProto_descriptor,
        new java.lang.String[] { "Id", "Name", "CategoryId", "MinPrice", "MaxPrice", "InStock", "Status", "Featured", "StartCreatedAt", "EndCreatedAt", "Page", "Size", "Sort", });
    internal_static_product_ListProductByIdsRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_product_ListProductByIdsRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_ListProductByIdsRequestProto_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_product_GetProductRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_product_GetProductRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_GetProductRequestProto_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_product_SearchProductRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_product_SearchProductRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_SearchProductRequestProto_descriptor,
        new java.lang.String[] { "Id", "Name", "CategoryId", "MinPrice", "MaxPrice", "InStock", "Status", "Featured", "StartCreatedAt", "EndCreatedAt", "Page", "Size", "Sort", });
    internal_static_product_CreateProductRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_product_CreateProductRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_CreateProductRequestProto_descriptor,
        new java.lang.String[] { "Name", "CategoryId", "Price", "Quantity", "Status", "Featured", });
    internal_static_product_CreateProductResponseProto_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_product_CreateProductResponseProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_CreateProductResponseProto_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_product_UpdateProductRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_product_UpdateProductRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_UpdateProductRequestProto_descriptor,
        new java.lang.String[] { "Id", "Name", "CategoryId", "Price", "Quantity", "Status", "Featured", });
    internal_static_product_DeleteProductRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_product_DeleteProductRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_DeleteProductRequestProto_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_product_ImportProductRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_product_ImportProductRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_ImportProductRequestProto_descriptor,
        new java.lang.String[] { "FileType", "FileContent", });
    internal_static_product_UploadImageRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_product_UploadImageRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_UploadImageRequestProto_descriptor,
        new java.lang.String[] { "Id", "FileContent", });
    internal_static_product_ExportProductRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_product_ExportProductRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_ExportProductRequestProto_descriptor,
        new java.lang.String[] { "Id", "Name", "CategoryId", "MinPrice", "MaxPrice", "InStock", "Status", "Featured", "StartCreatedAt", "EndCreatedAt", "Sort", "FileType", });
    internal_static_product_ExportProductResponseProto_descriptor =
      getDescriptor().getMessageTypes().get(16);
    internal_static_product_ExportProductResponseProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_ExportProductResponseProto_descriptor,
        new java.lang.String[] { "FileContent", });
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
