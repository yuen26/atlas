package org.atlas.framework.excel.poi.product;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.business.product.infrastructure.contract.excel.ExcelReader;
import org.atlas.shared.constant.Constant;
import org.atlas.shared.util.DateUtil;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductExcelReaderAdapter implements ExcelReader {

    private static final int BATCH_SIZE = 100;

    @Override
    public List<Product> read(byte[] fileContent) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(fileContent);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<Product> products = new ArrayList<>();
            int totalRows = sheet.getLastRowNum();
            int currentRow = 1; // Ignore header
            while (currentRow <= totalRows) {
                int endRow = Math.min(currentRow + BATCH_SIZE - 1, totalRows);
                for (int rowIndex = currentRow; rowIndex <= endRow; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (isNotEmptyRow(row)) {
                        Product product = readRow(row);
                        products.add(product);
                    }
                }
                currentRow += BATCH_SIZE;
            }
            return products;
        }
    }

    private boolean isNotEmptyRow(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.BLANK) {
                return true;
            }
        }
        return false;
    }

    private Product readRow(Row row) {
        Product product = new Product();
        product.setName(row.getCell(0).getStringCellValue());
        product.setCategory(new Category((int) row.getCell(1).getNumericCellValue()));
        product.setPrice(BigDecimal.valueOf(row.getCell(2).getNumericCellValue()));
        product.setQuantity((int) row.getCell(3).getNumericCellValue());
        product.setStatus(ProductStatus.valueOf(row.getCell(4).getStringCellValue()));
        product.setFeatured(row.getCell(5).getBooleanCellValue());
        product.setCreatedAt(DateUtil.parse(row.getCell(6).getStringCellValue(), Constant.DATE_TIME_FORMAT));
        return product;
    }
}
