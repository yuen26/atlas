package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.ExportOrderCommand;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.FindOrderCondition;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.FileType;
import org.atlas.business.order.infrastructure.contract.csv.CsvWriter;
import org.atlas.business.order.infrastructure.contract.excel.ExcelWriter;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.commons.utils.paging.PageDto;
import org.atlas.framework.command.contract.CommandExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExportOrderCommandExecutor implements CommandExecutor<ExportOrderCommand, byte[]> {

    private static final String EXCEL_SHEET_NAME = "Orders";

    private final OrderRepository orderRepository;
    private final CsvWriter csvWriter;
    private final ExcelWriter excelWriter;

    @Override
    @Transactional(readOnly = true)
    public byte[] execute(ExportOrderCommand command) throws Exception {
        FindOrderCondition condition = ModelMapperUtil.map(command, FindOrderCondition.class);
        condition.applyPaging(null, null, command.getSort());
        PageDto<Order> page = orderRepository.find(condition);
        return doExport(command.getFileType(), page.getRecords());
    }

    private byte[] doExport(FileType fileType, List<Order> orders) throws Exception {
        switch (fileType) {
            case CSV -> {
                return csvWriter.write(orders);
            }
            case EXCEL -> {
                return excelWriter.write(orders, EXCEL_SHEET_NAME);
            }
            default -> throw new UnsupportedOperationException("Unsupported file type");
        }
    }
}
