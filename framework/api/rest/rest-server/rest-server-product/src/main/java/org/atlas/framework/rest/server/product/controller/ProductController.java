package org.atlas.framework.rest.server.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.product.domain.shared.enums.FileType;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.rest.server.core.response.SkipRestResponseHandler;
import org.atlas.shared.util.DateUtil;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final CommandGateway commandGateway;

    @GetMapping("/list")
    public PageDto<ProductDto> listProduct(@Valid ListProductCommand command) throws Exception {
        return commandGateway.send(command);
    }

    @GetMapping("/list-by-ids")
    public List<ProductDto> listProductByIds(@RequestParam("ids") List<Integer> ids) throws Exception {
        ListProductByIdsCommand command = new ListProductByIdsCommand(ids);
        return commandGateway.send(command);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Integer id) throws Exception {
        GetProductCommand command = new GetProductCommand(id);
        return commandGateway.send(command);
    }

    @GetMapping("/search")
    public PageDto<ProductDto> searchProduct(@Valid SearchProductCommand command) throws Exception {
        return commandGateway.send(command);
    }

    /**
     * Request body as JSON string
     */
    @PostMapping("/v1")
    public Integer createProductV1(@Valid @RequestBody CreateProductCommand command) throws Exception {
        return commandGateway.send(command);
    }

    /**
     * Request body as x-www-form-urlencoded
     */
    @PostMapping("/v2")
    public Integer createProductV2(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "category_id") Integer categoryId,
                                   @RequestParam(name = "price") BigDecimal price,
                                   @RequestParam(name = "quantity") Integer quantity,
                                   @RequestParam(name = "status") ProductStatus status,
                                   @RequestParam(name = "featured") Boolean featured) throws Exception {
        CreateProductCommand command = new CreateProductCommand();
        command.setName(name);
        command.setCategoryId(categoryId);
        command.setPrice(price);
        command.setQuantity(quantity);
        command.setStatus(status);
        command.setFeatured(featured);
        return commandGateway.send(command);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") Integer id,
                              @Valid @RequestBody UpdateProductCommand command) throws Exception {
        command.setId(id);
        commandGateway.send(command);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Integer id) throws Exception {
        DeleteProductCommand command = new DeleteProductCommand(id);
        commandGateway.send(command);
    }

    @PostMapping("/import")
    public void importProduct(@RequestParam("file") MultipartFile file,
                              @RequestParam("fileType") FileType fileType) throws Exception {
        byte[] fileContent = file.getBytes();
        ImportProductCommand command = new ImportProductCommand(fileType, fileContent);
        commandGateway.send(command);
    }

    @GetMapping("/export")
    @SkipRestResponseHandler
    public ResponseEntity<byte[]> export(@Valid ExportProductCommand command) throws Exception {
        byte[] bytes = commandGateway.send(command);
        HttpHeaders headers = new HttpHeaders();
        String fileName = buildFileName(command);
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(bytes);
    }

    @PostMapping("/{id}/upload-image")
    public void uploadImage(@PathVariable("id") Integer id,
                            @RequestParam("file") MultipartFile file) throws Exception {
        byte[] fileContent = file.getBytes();
        UploadImageCommand command = new UploadImageCommand(id, fileContent);
        commandGateway.send(command);
    }

    private String buildFileName(ExportProductCommand command) {
        return "export-product-" + DateUtil.now("yyyyMMddHHmmss") + "." + command.getFileType().getExtension();
    }
}
