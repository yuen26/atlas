package org.atlas.framework.csv.opencsv.product;

import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.framework.csv.opencsv.core.OpenCsvWriter;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.infrastructure.contract.csv.CsvWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderCsvWriterAdapter implements CsvWriter {

    @Override
    public byte[] write(List<Order> orders) throws Exception {
        List<OrderWriteModel> csvOrders = orders.stream()
            .map(order -> ModelMapperUtil.map(order, OrderWriteModel.class))
            .toList();
        return OpenCsvWriter.write(csvOrders, OrderWriteModel.class);
    }
}
