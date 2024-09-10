package org.atlas.framework.csv.opencsv.product;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import org.atlas.business.order.domain.shared.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderWriteModel {

    @CsvBindByName(column = "Order ID")
    @CsvBindByPosition(position = 0)
    private Integer id;

    @CsvBindByName(column = "Customer ID")
    @CsvBindByPosition(position = 1)
    private Integer customerId;

    @CsvBindByName(column = "Amount")
    @CsvBindByPosition(position = 5)
    private BigDecimal amount;

    @CsvBindByName(column = "Address")
    @CsvBindByPosition(position = 6)
    private String address;

    @CsvBindByName(column = "Status")
    @CsvBindByPosition(position = 7)
    private OrderStatus status;

    @CsvBindByName(column = "Deleted")
    @CsvBindByPosition(position = 8)
    private Boolean deleted;

    @CsvBindByName(column = "Created At")
    @CsvBindByPosition(position = 6)
    @CsvDate("yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}