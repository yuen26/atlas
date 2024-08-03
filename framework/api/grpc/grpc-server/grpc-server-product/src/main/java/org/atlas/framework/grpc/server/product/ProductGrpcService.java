package org.atlas.framework.grpc.server.product;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.product.application.contract.command.CreateProductCommand;
import org.atlas.business.product.application.contract.command.DeleteProductCommand;
import org.atlas.business.product.application.contract.command.ExportProductCommand;
import org.atlas.business.product.application.contract.command.GetProductCommand;
import org.atlas.business.product.application.contract.command.ImportProductCommand;
import org.atlas.business.product.application.contract.command.ListProductByIdsCommand;
import org.atlas.business.product.application.contract.command.ListProductCommand;
import org.atlas.business.product.application.contract.command.SearchProductCommand;
import org.atlas.business.product.application.contract.command.UpdateProductCommand;
import org.atlas.business.product.application.contract.command.UploadImageCommand;
import org.atlas.business.product.application.contract.model.CategoryDto;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.product.domain.shared.enums.FileType;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.grpc.protobuf.product.CategoryProto;
import org.atlas.framework.grpc.protobuf.product.CreateProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.DeleteProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.ExportProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.ExportProductResponseProto;
import org.atlas.framework.grpc.protobuf.product.GetProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.ImportProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.ListProductByIdsRequestProto;
import org.atlas.framework.grpc.protobuf.product.ListProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.ProductListProto;
import org.atlas.framework.grpc.protobuf.product.ProductPageProto;
import org.atlas.framework.grpc.protobuf.product.ProductProto;
import org.atlas.framework.grpc.protobuf.product.ProductServiceGrpc;
import org.atlas.framework.grpc.protobuf.product.SearchProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.UpdateProductRequestProto;
import org.atlas.framework.grpc.protobuf.product.UploadImageRequestProto;
import org.atlas.shared.constant.Constant;
import org.atlas.shared.util.DateUtil;
import org.atlas.shared.util.paging.PageDto;

import java.math.BigDecimal;
import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class ProductGrpcService extends ProductServiceGrpc.ProductServiceImplBase {

    private final CommandGateway commandGateway;

    @Override
    public void listProduct(ListProductRequestProto requestProto,
                            StreamObserver<ProductPageProto> responseObserver) {
        ListProductCommand command = map(requestProto);
        try {
            PageDto<ProductDto> productPageDto = commandGateway.send(command);
            ProductPageProto responseProto = map(productPageDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void listProductByIds(ListProductByIdsRequestProto requestProto,
                                 StreamObserver<ProductListProto> responseObserver) {
        ListProductByIdsCommand command = map(requestProto);
        try {
            List<ProductDto> productDtoList = commandGateway.send(command);
            ProductListProto responseProto = map(productDtoList);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getProduct(GetProductRequestProto requestProto,
                           StreamObserver<ProductProto> responseObserver) {
        GetProductCommand command = map(requestProto);
        try {
            ProductDto productDto = commandGateway.send(command);
            ProductProto responseProto = map(productDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void searchProduct(SearchProductRequestProto requestProto,
                              StreamObserver<ProductPageProto> responseObserver) {
        SearchProductCommand command = map(requestProto);
        try {
            PageDto<ProductDto> productPageDto = commandGateway.send(command);
            ProductPageProto responseProto = map(productPageDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createProduct(CreateProductRequestProto requestProto,
                              StreamObserver<Empty> responseObserver) {
        CreateProductCommand command = map(requestProto);
        try {
            commandGateway.send(command);
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(UpdateProductRequestProto requestProto,
                              StreamObserver<Empty> responseObserver) {
        UpdateProductCommand command = map(requestProto);
        try {
            commandGateway.send(command);
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(DeleteProductRequestProto requestProto,
                              StreamObserver<Empty> responseObserver) {
        DeleteProductCommand command = map(requestProto);
        try {
            commandGateway.send(command);
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void uploadImage(UploadImageRequestProto requestProto,
                            StreamObserver<Empty> responseObserver) {
        UploadImageCommand command = map(requestProto);
        try {
            commandGateway.send(command);
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exportProduct(ExportProductRequestProto request,
                              StreamObserver<ExportProductResponseProto> responseObserver) {
        ExportProductCommand command = map(request);
        try {
            byte[] fileContent = commandGateway.send(command);
            ExportProductResponseProto requestProto = ExportProductResponseProto.parseFrom(fileContent);
            responseObserver.onNext(requestProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void importProduct(ImportProductRequestProto requestProto,
                              StreamObserver<Empty> responseObserver) {
        ImportProductCommand command = map(requestProto);
        try {
            commandGateway.send(command);
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ListProductCommand map(ListProductRequestProto requestProto) {
        ListProductCommand command = new ListProductCommand();
        command.setId(requestProto.getId());
        command.setName(requestProto.getName());
        command.setCategoryId(requestProto.getCategoryId());
        command.setMinPrice(BigDecimal.valueOf(requestProto.getMinPrice()));
        command.setMaxPrice(BigDecimal.valueOf(requestProto.getMaxPrice()));
        command.setInStock(requestProto.getInStock());
        command.setStatus(ProductStatus.valueOf(requestProto.getStatus()));
        command.setFeatured(requestProto.getFeatured());
        command.setStartCreatedAt(DateUtil.parse(requestProto.getStartCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setEndCreatedAt(DateUtil.parse(requestProto.getEndCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setPage(requestProto.getPage());
        command.setSize(requestProto.getSize());
        command.setSort(requestProto.getSort());
        return command;
    }

    private ListProductByIdsCommand map(ListProductByIdsRequestProto requestProto) {
        return new ListProductByIdsCommand(requestProto.getIdList());
    }

    private GetProductCommand map(GetProductRequestProto requestProto) {
        return new GetProductCommand(requestProto.getId());
    }

    private SearchProductCommand map(SearchProductRequestProto requestProto) {
        SearchProductCommand command = new SearchProductCommand();
        command.setId(requestProto.getId());
        command.setName(requestProto.getName());
        command.setCategoryId(requestProto.getCategoryId());
        command.setMinPrice(BigDecimal.valueOf(requestProto.getMinPrice()));
        command.setMaxPrice(BigDecimal.valueOf(requestProto.getMaxPrice()));
        command.setInStock(requestProto.getInStock());
        command.setStatus(ProductStatus.valueOf(requestProto.getStatus()));
        command.setFeatured(requestProto.getFeatured());
        command.setStartCreatedAt(DateUtil.parse(requestProto.getStartCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setEndCreatedAt(DateUtil.parse(requestProto.getEndCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setPage(requestProto.getPage());
        command.setSize(requestProto.getSize());
        command.setSort(requestProto.getSort());
        return command;
    }

    private ExportProductCommand map(ExportProductRequestProto requestProto) {
        ExportProductCommand command = new ExportProductCommand();
        command.setId(requestProto.getId());
        command.setName(requestProto.getName());
        command.setCategoryId(requestProto.getCategoryId());
        command.setMinPrice(BigDecimal.valueOf(requestProto.getMinPrice()));
        command.setMaxPrice(BigDecimal.valueOf(requestProto.getMaxPrice()));
        command.setInStock(requestProto.getInStock());
        command.setStatus(ProductStatus.valueOf(requestProto.getStatus()));
        command.setFeatured(requestProto.getFeatured());
        command.setStartCreatedAt(DateUtil.parse(requestProto.getStartCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setEndCreatedAt(DateUtil.parse(requestProto.getEndCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setSort(requestProto.getSort());
        command.setFileType(FileType.of(requestProto.getFileType()));
        return command;
    }

    private CreateProductCommand map(CreateProductRequestProto requestProto) {
        CreateProductCommand command = new CreateProductCommand();
        command.setName(requestProto.getName());
        command.setCategoryId(requestProto.getCategoryId());
        command.setPrice(BigDecimal.valueOf(requestProto.getPrice()));
        command.setQuantity(requestProto.getQuantity());
        command.setStatus(ProductStatus.valueOf(requestProto.getStatus()));
        command.setFeatured(requestProto.getFeatured());
        return command;
    }

    private UpdateProductCommand map(UpdateProductRequestProto requestProto) {
        UpdateProductCommand command = new UpdateProductCommand();
        command.setId(requestProto.getId());
        command.setName(requestProto.getName());
        command.setCategoryId(requestProto.getCategoryId());
        command.setPrice(BigDecimal.valueOf(requestProto.getPrice()));
        command.setQuantity(requestProto.getQuantity());
        command.setStatus(ProductStatus.valueOf(requestProto.getStatus()));
        command.setFeatured(requestProto.getFeatured());
        return command;
    }

    private DeleteProductCommand map(DeleteProductRequestProto requestProto) {
        return new DeleteProductCommand(requestProto.getId());
    }

    private ImportProductCommand map(ImportProductRequestProto requestProto) {
        return new ImportProductCommand(
            FileType.of(requestProto.getFileType()),
            requestProto.getFileContent().toByteArray()
        );
    }

    private UploadImageCommand map(UploadImageRequestProto requestProto) {
        return new UploadImageCommand(
            requestProto.getId(),
            requestProto.getFileContent().toByteArray()
        );
    }

    private ProductProto map(ProductDto productDto) {
        return ProductProto.newBuilder()
            .setId(productDto.getId())
            .setName(productDto.getName())
            .setCategory(map(productDto.getCategory()))
            .setPrice(productDto.getPrice().doubleValue())
            .setQuantity(productDto.getQuantity())
            .setStatus(productDto.getStatus().name())
            .setFeatured(productDto.getFeatured())
            .setCreatedAt(DateUtil.format(productDto.getCreatedAt(), Constant.DATE_TIME_FORMAT))
            .setUpdatedAt(DateUtil.format(productDto.getUpdatedAt(), Constant.DATE_TIME_FORMAT))
            .build();
    }

    private CategoryProto map(CategoryDto categoryDto) {
        return CategoryProto.newBuilder()
            .setId(categoryDto.getId())
            .setName(categoryDto.getName())
            .build();
    }

    private ProductPageProto map(PageDto<ProductDto> productDtoPage) {
        if (productDtoPage.isEmpty()) {
            return ProductPageProto.getDefaultInstance();
        }
        ProductPageProto.Builder builder = ProductPageProto.newBuilder();
        productDtoPage.getRecords().forEach(productDto -> builder.addProduct(map(productDto)));
        builder.setTotalCount(productDtoPage.getTotalCount());
        return builder.build();
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
