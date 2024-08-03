package org.atlas.framework.csv.opencsv.product;

import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.infrastructure.contract.csv.CsvReader;
import org.atlas.framework.csv.opencsv.core.OpenCsvReader;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ProductCsvReaderAdapter implements CsvReader {

    @Override
    public List<Product> read(byte[] fileContent) throws IOException {
        List<ProductReadModel> csvProducts = OpenCsvReader.read(fileContent, ProductReadModel.class);
        return csvProducts.stream()
            .map(csvProduct -> {
                Product product = ModelMapperUtil.map(csvProduct, Product.class);
                product.setCategory(new Category(csvProduct.getCategoryId()));
                return product;
            })
            .toList();
    }
}
