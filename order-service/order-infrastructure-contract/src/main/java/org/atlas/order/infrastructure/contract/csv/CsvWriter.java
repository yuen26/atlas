package org.atlas.order.infrastructure.contract.csv;

import org.atlas.order.domain.entity.Order;

import java.util.List;

public interface CsvWriter {

    byte[] write(List<Order> orders) throws Exception;
}
