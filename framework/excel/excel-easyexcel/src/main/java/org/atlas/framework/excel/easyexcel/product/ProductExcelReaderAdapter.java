package org.atlas.framework.excel.easyexcel.product;

import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.infrastructure.contract.excel.ExcelReader;
import org.atlas.framework.excel.easyexcel.core.EasyExcelReader;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ProductExcelReaderAdapter implements ExcelReader {

    @Override
    public List<Product> read(byte[] fileContent) throws IOException {
        List<ProductReadModel> excelProducts = EasyExcelReader.read(fileContent, ProductReadModel.class);
        return excelProducts.stream()
            .map(excelProduct -> {
                Product product = ModelMapperUtil.map(excelProduct, Product.class);
                product.setCategory(new Category(excelProduct.getCategoryId()));
                return product;
            })
            .toList();
    }
}
