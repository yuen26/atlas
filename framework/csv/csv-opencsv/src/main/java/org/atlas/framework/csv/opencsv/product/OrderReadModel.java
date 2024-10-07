package org.atlas.framework.csv.opencsv.product;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.util.Date;

@Data
public class OrderReadModel {

    @CsvBindByName(column = "Order No")
    @CsvBindByPosition(position = 0)
    private String orderNo;

    @CsvBindByName(column = "User ID")
    @CsvBindByPosition(position = 1)
    private Integer userId;

    @CsvBindByName(column = "Product ID")
    @CsvBindByPosition(position = 2)
    private Integer productId;

    @CsvBindByName(column = "Quantity")
    @CsvBindByPosition(position = 3)
    private Integer quantity;

    @CsvBindByName(column = "Address")
    @CsvBindByPosition(position = 4)
    private String address;

    @CsvBindByName(column = "Created At")
    @CsvBindByPosition(position = 5)
    @CsvDate("yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}
