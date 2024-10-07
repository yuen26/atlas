package org.atlas.order.infrastructure.contract.excel;

import org.atlas.order.domain.entity.Order;

import java.io.IOException;
import java.util.List;

public interface ExcelReader {

    List<Order> read(byte[] fileContent) throws IOException;
}
