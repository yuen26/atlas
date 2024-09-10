package org.atlas.business.order.infrastructure.contract.excel;

import org.atlas.business.order.domain.entity.Order;

import java.io.IOException;
import java.util.List;

public interface ExcelReader {

    List<Order> read(byte[] fileContent) throws IOException;
}
