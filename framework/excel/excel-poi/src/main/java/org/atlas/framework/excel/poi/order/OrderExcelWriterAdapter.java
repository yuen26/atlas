package org.atlas.framework.excel.poi.order;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.atlas.commons.constant.Constant;
import org.atlas.commons.utils.DateUtil;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.infrastructure.contract.excel.ExcelWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class OrderExcelWriterAdapter implements ExcelWriter {

    @Override
    public byte[] write(List<Order> orders, String sheetName) throws Exception {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            XSSFSheet sheet = workbook.createSheet(sheetName);
            createHeader(workbook, sheet);
            createRows(workbook, sheet, orders);
            sheet.autoSizeColumn(1);
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    private void createHeader(XSSFWorkbook workbook, XSSFSheet sheet) {
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("Calibri");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Order ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Customer ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Amount");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Address");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Status");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(5);
        headerCell.setCellValue("Created At");
        headerCell.setCellStyle(headerStyle);
    }

    private void createRows(XSSFWorkbook workbook, XSSFSheet sheet, List<Order> orders) {
        int rowIndex = 1;
        for (Order order : orders) {
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.LEFT);
            style.setWrapText(true);

            Row row = sheet.createRow(rowIndex);
            Cell cell = row.createCell(0);
            cell.setCellValue(order.getId());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(order.getUserId());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(order.getAmount().doubleValue());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(order.getAddress());
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(order.getStatus().name());
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(DateUtil.format(order.getCreatedAt(), Constant.DATE_TIME_FORMAT));
            cell.setCellStyle(style);

            rowIndex++;
        }
    }
}
