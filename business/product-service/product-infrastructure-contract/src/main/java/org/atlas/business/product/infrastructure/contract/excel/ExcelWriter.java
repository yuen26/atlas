package org.atlas.business.product.infrastructure.contract.excel;

import org.atlas.business.product.domain.entity.Product;

import java.util.List;

public interface ExcelWriter {

    byte[] write(List<Product> products, String sheetName) throws Exception;
}
