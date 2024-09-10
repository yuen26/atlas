package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.order.application.contract.command.ImportOrderCommand;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.FileType;
import org.atlas.business.order.infrastructure.contract.csv.CsvReader;
import org.atlas.business.order.infrastructure.contract.excel.ExcelReader;
import org.atlas.framework.command.contract.CommandExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImportOrderCommandExecutor implements CommandExecutor<ImportOrderCommand, Void> {

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

        // Perform import logic
        if (CollectionUtils.isNotEmpty(orders)) {
            for (Order order : orders) {
                orderRepository.insert(order);
                orderService.postCreateOrder(order);
            }
        }
        log.info("Imported {} orders from file", orders.size());

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
}
