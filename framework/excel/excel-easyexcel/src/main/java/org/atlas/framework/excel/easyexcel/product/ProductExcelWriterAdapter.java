package org.atlas.framework.excel.easyexcel.product;

import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.infrastructure.contract.excel.ExcelWriter;
import org.atlas.framework.excel.easyexcel.core.EasyExcelWriter;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductExcelWriterAdapter implements ExcelWriter {

    @Override
    public byte[] write(List<Product> products, String sheetName) throws Exception {
        List<ProductWriteModel> excelProducts = products.stream()
            .map(product -> {
                ProductWriteModel excelProduct = ModelMapperUtil.map(product, ProductWriteModel.class);
                excelProduct.setCategoryName(product.getCategory().getName());
                return excelProduct;
            })
            .toList();
        return EasyExcelWriter.write(excelProducts, "Products", ProductWriteModel.class);
    }
}
