package org.atlas.framework.grpc.server.product;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.product.application.contract.command.ListProductCommand;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.grpc.protobuf.product.ListProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.ProductListProto;
import org.atlas.framework.grpc.protobuf.product.ProductProto;
import org.atlas.framework.grpc.protobuf.product.ProductServiceGrpc;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class ProductGrpcService extends ProductServiceGrpc.ProductServiceImplBase {

    private final CommandGateway commandGateway;

    @Override
    public void listProduct(ListProductRequestProto requestProto,
                            StreamObserver<ProductListProto> responseObserver) {
        ListProductCommand command = map(requestProto);
        try {
            List<ProductDto> productDtoList = commandGateway.send(command);
            ProductListProto responseProto = map(productDtoList);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ListProductCommand map(ListProductRequestProto requestProto) {
        return new ListProductCommand(requestProto.getIdList());
    }

    private ProductProto map(ProductDto productDto) {
        return ProductProto.newBuilder()
            .setId(productDto.getId())
            .setName(productDto.getName())
            .setPrice(productDto.getPrice().doubleValue())
            .build();
    }

    private ProductListProto map(List<ProductDto> productDtoList) {
        if (CollectionUtils.isEmpty(productDtoList)) {
            return ProductListProto.getDefaultInstance();
        }
        ProductListProto.Builder builder = ProductListProto.newBuilder();
        productDtoList.forEach(productDto -> builder.addProduct(map(productDto)));
        return builder.build();
    }
}
