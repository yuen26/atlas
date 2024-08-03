package org.atlas.business.product.infrastructure.contract.csv;

import org.atlas.business.product.domain.entity.Product;

import java.util.List;

public interface CsvWriter {

    byte[] write(List<Product> products) throws Exception;
}
