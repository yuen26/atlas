package org.atlas.business.order.infrastructure.contract.csv;

import org.atlas.business.order.domain.entity.Order;

import java.io.IOException;
import java.util.List;

public interface CsvReader {

    List<Order> read(byte[] fileContent) throws IOException;
}
