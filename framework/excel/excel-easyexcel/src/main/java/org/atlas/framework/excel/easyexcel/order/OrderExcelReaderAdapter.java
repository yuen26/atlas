package org.atlas.framework.excel.easyexcel.order;

import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.infrastructure.contract.excel.ExcelReader;
import org.atlas.framework.excel.easyexcel.core.EasyExcelReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderExcelReaderAdapter implements ExcelReader {

    @Override
    public List<Order> read(byte[] fileContent) throws IOException {
        List<OrderReadModel> excelOrders = EasyExcelReader.read(fileContent, OrderReadModel.class);
        if (CollectionUtils.isEmpty(excelOrders)) {
            return Collections.emptyList();
        }

        Map<String, Order> orderMap = new LinkedHashMap<>();
        for (OrderReadModel excelOrder : excelOrders) {
            Order order = orderMap.computeIfAbsent(excelOrder.getOrderNo(), orderNo -> {
                Order newOrder = new Order();
                newOrder.setCustomerId(excelOrder.getCustomerId());
                newOrder.setAddress(excelOrder.getAddress());
                newOrder.setCreatedAt(excelOrder.getCreatedAt());
                return newOrder;
            });

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(excelOrder.getProductId());
            orderItem.setQuantity(excelOrder.getQuantity());
            order.addOrderItem(orderItem);
        }
        return (List<Order>) orderMap.values();
    }
}
