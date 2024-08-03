package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.ExportProductCommand;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.FindProductCondition;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.business.product.domain.shared.enums.FileType;
import org.atlas.business.product.infrastructure.contract.csv.CsvWriter;
import org.atlas.business.product.infrastructure.contract.excel.ExcelWriter;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.util.ModelMapperUtil;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExportProductCommandExecutor implements CommandExecutor<ExportProductCommand, byte[]> {

    private static final String EXCEL_SHEET_NAME = "Products";

    private final ProductRepository productRepository;
    private final CsvWriter csvWriter;
    private final ExcelWriter excelWriter;

    @Override
    @Transactional(readOnly = true)
    public byte[] execute(ExportProductCommand command) throws Exception {
        FindProductCondition condition = ModelMapperUtil.map(command, FindProductCondition.class);
        PageDto<Product> page = productRepository.find(condition);
        return doExport(command.getFileType(), page.getRecords());
    }

    private byte[] doExport(FileType fileType, List<Product> products) throws Exception {
        switch (fileType) {
            case CSV -> {
                return csvWriter.write(products);
            }
            case EXCEL -> {
                return excelWriter.write(products, EXCEL_SHEET_NAME);
            }
            default -> throw new UnsupportedOperationException("Unsupported file type");
        }
    }
}
