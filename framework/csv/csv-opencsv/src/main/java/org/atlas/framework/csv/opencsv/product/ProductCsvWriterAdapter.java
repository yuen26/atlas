package org.atlas.framework.csv.opencsv.product;

import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.infrastructure.contract.csv.CsvWriter;
import org.atlas.framework.csv.opencsv.core.OpenCsvWriter;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCsvWriterAdapter implements CsvWriter {

    @Override
    public byte[] write(List<Product> products) throws Exception {
        List<ProductWriteModel> csvProducts = products.stream()
            .map(product -> {
                ProductWriteModel csvProduct = ModelMapperUtil.map(product, ProductWriteModel.class);
                csvProduct.setCategory(product.getCategory().getName());
                return csvProduct;
            })
            .toList();
        return OpenCsvWriter.write(csvProducts, ProductWriteModel.class);
    }
}
