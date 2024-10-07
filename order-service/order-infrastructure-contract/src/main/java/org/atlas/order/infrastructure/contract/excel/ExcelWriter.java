package org.atlas.order.infrastructure.contract.excel;

import org.atlas.order.domain.entity.Order;

import java.util.List;

public interface ExcelWriter {

    byte[] write(List<Order> products, String sheetName) throws Exception;
}
