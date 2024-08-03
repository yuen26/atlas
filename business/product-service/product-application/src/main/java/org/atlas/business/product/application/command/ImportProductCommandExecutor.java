package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.product.application.contract.command.ImportProductCommand;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.business.product.domain.shared.enums.FileType;
import org.atlas.business.product.infrastructure.contract.csv.CsvReader;
import org.atlas.business.product.infrastructure.contract.excel.ExcelReader;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.framework.event.contract.product.ProductCreatedEvent;
import org.atlas.framework.event.contract.product.model.ProductData;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImportProductCommandExecutor implements CommandExecutor<ImportProductCommand, Void> {

    private final CsvReader csvReader;
    private final ExcelReader excelReader;
    private final ProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Void execute(ImportProductCommand command) {
        // Read products from file
        List<Product> products;
        try {
            products = readProducts(command.getFileType(), command.getFileContent());
            log.info("Read {} products from file", CollectionUtils.size(products));
        } catch (Exception e) {
            log.error("Failed to read products");
            return null;
        }

        // Perform import logic
        if (CollectionUtils.isNotEmpty(products)) {
            for (Product product : products) {
                productRepository.insert(product);

                ProductCreatedEvent event = newEvent(product);
                applicationEventPublisher.publishEvent(event);
            }
        }
        log.info("Imported {} products from file", products.size());

        return null;
    }

    private List<Product> readProducts(FileType fileType, byte[] fileContent) throws Exception {
        switch (fileType) {
            case CSV -> {
                return csvReader.read(fileContent);
            }
            case EXCEL -> {
                return excelReader.read(fileContent);
            }
            default -> throw new UnsupportedOperationException("Unsupported file type");
        }
    }

    private ProductCreatedEvent newEvent(Product product) {
        ProductData productData = ModelMapperUtil.map(product, ProductData.class);
        return new ProductCreatedEvent(productData);
    }
}
