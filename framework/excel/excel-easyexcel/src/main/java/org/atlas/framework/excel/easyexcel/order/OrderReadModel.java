package org.atlas.framework.excel.easyexcel.order;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OrderReadModel {

    @ExcelProperty(value = "Order No")
    private String orderNo;

    @ExcelProperty(value = "Customer ID")
    private Integer customerId;

    @ExcelProperty(value = "Product ID")
    private Integer productId;

    @ExcelProperty(value = "Quantity")
    private Integer quantity;

    @ExcelProperty(value = "Address")
    private String address;

    @ExcelProperty(value = "Created At")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}
