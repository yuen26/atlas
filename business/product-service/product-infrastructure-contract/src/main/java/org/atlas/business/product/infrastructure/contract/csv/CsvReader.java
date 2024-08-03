package org.atlas.business.product.infrastructure.contract.csv;

import org.atlas.business.product.domain.entity.Product;

import java.io.IOException;
import java.util.List;

public interface CsvReader {

    List<Product> read(byte[] fileContent) throws IOException;
}
