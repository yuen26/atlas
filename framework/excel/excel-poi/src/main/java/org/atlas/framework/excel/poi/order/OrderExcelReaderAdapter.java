package org.atlas.framework.excel.poi.order;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.infrastructure.contract.excel.ExcelReader;
import org.atlas.commons.constant.Constant;
import org.atlas.commons.utils.DateUtil;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderExcelReaderAdapter implements ExcelReader {

    private static final int BATCH_SIZE = 100;

    @Override
    public List<Order> read(byte[] fileContent) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(fileContent);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Map<String, Order> orderMap = new LinkedHashMap<>();
            int totalRows = sheet.getLastRowNum();
            int currentRow = 1; // Ignore header
            while (currentRow <= totalRows) {
                int endRow = Math.min(currentRow + BATCH_SIZE - 1, totalRows);
                for (int rowIndex = currentRow; rowIndex <= endRow; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (isNotEmptyRow(row)) {
                        OrderReadModel excelOrder = readRow(row);

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
                }
                currentRow += BATCH_SIZE;
            }
            return (List<Order>) orderMap.values();
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

    private OrderReadModel readRow(Row row) {
        OrderReadModel product = new OrderReadModel();
        product.setOrderNo(row.getCell(0).getStringCellValue());
        product.setCustomerId((int) row.getCell(1).getNumericCellValue());
        product.setProductId((int) (row.getCell(2).getNumericCellValue()));
        product.setQuantity((int) row.getCell(3).getNumericCellValue());
        product.setAddress(row.getCell(4).getStringCellValue());
        product.setCreatedAt(DateUtil.parse(row.getCell(5).getStringCellValue(), Constant.DATE_TIME_FORMAT));
        return product;
    }
}
