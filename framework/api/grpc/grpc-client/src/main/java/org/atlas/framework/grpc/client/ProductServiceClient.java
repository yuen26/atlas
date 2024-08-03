package org.atlas.framework.grpc.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.atlas.business.product.application.contract.model.CategoryDto;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.framework.api.client.contract.IProductServiceClient;
import org.atlas.framework.grpc.protobuf.product.CategoryProto;
import org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto;
import org.atlas.framework.grpc.protobuf.product.ProductListProto;
import org.atlas.framework.grpc.protobuf.product.ProductProto;
import org.atlas.framework.grpc.protobuf.product.ProductServiceGrpc;
import org.atlas.shared.constant.Constant;
import org.atlas.shared.util.DateUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceClient implements IProductServiceClient {

    @GrpcClient("product")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    @Override
    public List<ProductDto> listProductByIds(List<Integer> ids) {
        ListProductByIdsRequestProto requestProto = map(ids);
        ProductListProto responseProto = productServiceBlockingStub.listProductByIds(requestProto);
        return map(responseProto);
    }

    private ListProductByIdsRequestProto map(List<Integer> ids) {
        return ListProductByIdsRequestProto.newBuilder()
            .addAllId(ids)
            .build();
    }

    private List<ProductDto> map(ProductListProto productListProto) {
        return productListProto.getProductList()
            .stream()
            .map(this::map)
            .toList();
    }

    private ProductDto map(ProductProto productProto) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productProto.getId());
        productDto.setName(productProto.getName());
        productDto.setCategory(map(productProto.getCategory()));
        productDto.setPrice(BigDecimal.valueOf(productProto.getPrice()));
        productDto.setQuantity(productProto.getQuantity());
        productDto.setStatus(ProductStatus.valueOf(productProto.getStatus()));
        productDto.setFeatured(productProto.getFeatured());
        productDto.setCreatedAt(DateUtil.parse(productProto.getCreatedAt(), Constant.DATE_TIME_FORMAT));
        productDto.setUpdatedAt(DateUtil.parse(productProto.getUpdatedAt(), Constant.DATE_TIME_FORMAT));
        return productDto;
    }

    private CategoryDto map(CategoryProto categoryProto) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryProto.getId());
        categoryDto.setName(categoryProto.getName());
        return categoryDto;
    }
}
