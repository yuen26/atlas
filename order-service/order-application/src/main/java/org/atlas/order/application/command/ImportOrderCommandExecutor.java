package org.atlas.order.application.command;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.framework.command.executor.CommandExecutor;
import org.atlas.order.application.contract.command.ImportOrderCommand;
import org.atlas.order.application.contract.model.OrderDto;
import org.atlas.order.application.service.OrderService;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.repository.OrderRepository;
import org.atlas.order.domain.shared.enums.FileType;
import org.atlas.order.infrastructure.contract.csv.CsvReader;
import org.atlas.order.infrastructure.contract.excel.ExcelReader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImportOrderCommandExecutor implements CommandExecutor<ImportOrderCommand, Void> {

    private static final int BATCH_SIZE = 100;

    private final CsvReader csvReader;
    private final ExcelReader excelReader;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    public Void execute(ImportOrderCommand command) {
        // Read orders from file
        List<Order> orders;
        try {
            orders = readOrders(command.getFileType(), command.getFileContent());
            log.info("Read {} orders from file", CollectionUtils.size(orders));
        } catch (Exception e) {
            log.error("Failed to read orders");
            return null;
        }

        if (CollectionUtils.isEmpty(orders)) {
            log.warn("Not found any order that needs importing");
            return null;
        }

        List<List<Order>> batches = splitBatches(orders);
        log.info("Split orders into {} batches", batches.size());

        AtomicInteger counter = new AtomicInteger(0);
        batches.forEach(batch -> {
            List<OrderDto> orderDtoList = orderService.aggregate(orders);
            Map<Integer, OrderDto> orderDtoMap = orderDtoList.stream()
                .collect(Collectors.toMap(OrderDto::getId, Function.identity()));
            orders.forEach(order -> {
                orderRepository.insert(order);
                OrderDto orderDto = orderDtoMap.get(order.getId());
                orderService.postCreateOrder(orderDto);
            });
            log.info("Imported batch {}", counter.incrementAndGet());
        });

        log.info("Imported all {} orders from file", orders.size());
        return null;
    }

    private List<Order> readOrders(FileType fileType, byte[] fileContent) throws Exception {
        switch (fileType) {
            case CSV -> {
                return csvReader.read(fileContent);
            }
            case EXCEL -> {
                return excelReader.read(fileContent);
            }
            default -> throw new UnsupportedOperationException("Unsupported file type");
        }
    }

    private List<List<Order>> splitBatches(List<Order> orders) {
        return Lists.partition(orders, BATCH_SIZE);
    }
}
