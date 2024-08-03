package org.atlas.framework.excel.easyexcel.product;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;
import org.atlas.business.product.domain.shared.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductReadModel {

    @ExcelProperty(value = "ID")
    private Integer id;

    @ExcelProperty(value = "Name")
    private String name;

    @ExcelProperty(value = "Category")
    private Integer categoryId;

    @ExcelProperty(value = "Price")
    private BigDecimal price;

    @ExcelProperty(value = "Quantity")
    private Integer quantity;

    @ExcelProperty(value = "Status", converter = ProductStatusConverter.class)
    private ProductStatus status;

    @ExcelProperty(value = "Featured")
    private Boolean featured;

    @ExcelProperty(value = "Created At")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}
