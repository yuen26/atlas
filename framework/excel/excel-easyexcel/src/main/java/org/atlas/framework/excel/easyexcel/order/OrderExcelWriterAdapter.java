package org.atlas.framework.excel.easyexcel.order;

import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.infrastructure.contract.excel.ExcelWriter;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.framework.excel.easyexcel.core.EasyExcelWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderExcelWriterAdapter implements ExcelWriter {

    @Override
    public byte[] write(List<Order> orders, String sheetName) throws Exception {
        List<OrderWriteModel> excelOrders = orders.stream()
            .map(order -> ModelMapperUtil.map(order, OrderWriteModel.class))
            .toList();
        return EasyExcelWriter.write(excelOrders, "Orders", OrderWriteModel.class);
    }
}
