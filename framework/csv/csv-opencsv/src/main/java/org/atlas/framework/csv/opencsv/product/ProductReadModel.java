package org.atlas.framework.csv.opencsv.product;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import org.atlas.business.product.domain.shared.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductReadModel {

    @CsvBindByName(column = "Name")
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByName(column = "Category")
    @CsvBindByPosition(position = 1)
    private Integer categoryId;

    @CsvBindByName(column = "Price")
    @CsvBindByPosition(position = 2)
    private BigDecimal price;

    @CsvBindByName(column = "Quantity")
    @CsvBindByPosition(position = 3)
    private Integer quantity;

    @CsvBindByName(column = "Status")
    @CsvBindByPosition(position = 4)
    private ProductStatus status;

    @CsvBindByName(column = "Featured")
    @CsvBindByPosition(position = 5)
    private Boolean featured;

    @CsvBindByName(column = "Created At")
    @CsvBindByPosition(position = 6)
    @CsvDate("yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}