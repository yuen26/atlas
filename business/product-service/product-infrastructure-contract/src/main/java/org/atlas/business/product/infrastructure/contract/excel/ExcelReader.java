package org.atlas.business.product.infrastructure.contract.excel;

import org.atlas.business.product.domain.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ExcelReader {

    List<Product> read(byte[] fileContent) throws IOException;
}
