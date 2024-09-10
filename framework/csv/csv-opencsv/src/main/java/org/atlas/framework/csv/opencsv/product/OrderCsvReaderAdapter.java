package org.atlas.framework.csv.opencsv.product;

import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.infrastructure.contract.csv.CsvReader;
import org.atlas.framework.csv.opencsv.core.OpenCsvReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderCsvReaderAdapter implements CsvReader {

    @Override
    public List<Order> read(byte[] fileContent) throws IOException {
        List<OrderReadModel> csvProducts = OpenCsvReader.read(fileContent, OrderReadModel.class);
        if (CollectionUtils.isEmpty(csvProducts)) {
            return Collections.emptyList();
        }

        Map<String, Order> orderMap = new LinkedHashMap<>();
        for (OrderReadModel csvProduct : csvProducts) {
            Order order = orderMap.computeIfAbsent(csvProduct.getOrderNo(), orderNo -> {
                Order newOrder = new Order();
                newOrder.setCustomerId(csvProduct.getCustomerId());
                newOrder.setAddress(csvProduct.getAddress());
                newOrder.setCreatedAt(csvProduct.getCreatedAt());
                return newOrder;
            });

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(csvProduct.getProductId());
            orderItem.setQuantity(csvProduct.getQuantity());
            order.addOrderItem(orderItem);
        }
        return (List<Order>) orderMap.values();
    }
}
