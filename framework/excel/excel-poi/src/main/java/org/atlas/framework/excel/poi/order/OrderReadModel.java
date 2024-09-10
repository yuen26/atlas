package org.atlas.framework.excel.poi.order;

import lombok.Data;

import java.util.Date;

@Data
public class OrderReadModel {

    private String orderNo;
    private Integer customerId;
    private Integer productId;
    private Integer quantity;
    private String address;
    private Date createdAt;
}
