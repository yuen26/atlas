package org.atlas.framework.grpc.client.product;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.atlas.framework.api.client.contract.product.IProductServiceClient;
import org.atlas.framework.grpc.protobuf.product.ListProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.ProductListProto;
import org.atlas.framework.grpc.protobuf.product.ProductProto;
import org.atlas.framework.grpc.protobuf.product.ProductServiceGrpc;
import org.atlas.product.application.contract.model.ProductDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceClient implements IProductServiceClient {

    @GrpcClient("product")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    @Override
    public List<ProductDto> listProduct(List<Integer> ids) {
        ListProductRequestProto requestProto = map(ids);
        ProductListProto responseProto = productServiceBlockingStub.listProduct(requestProto);
        return map(responseProto);
    }

    private ListProductRequestProto map(List<Integer> ids) {
        return ListProductRequestProto.newBuilder()
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
        productDto.setPrice(BigDecimal.valueOf(productProto.getPrice()));
        return productDto;
    }
}
