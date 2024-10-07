package org.atlas.order.domain.repository;

import lombok.Data;
import org.atlas.order.domain.shared.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FindOrderCondition {

    private Integer id;
    private Integer userId;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private String address;
    private OrderStatus status;
    private Date startCreatedAt;
    private Date endCreatedAt;
    private Integer limit;
    private Integer offset;
    private String sortBy;
    private boolean sortAscending;

    public void applyPaging(Integer page, Integer size, String sort) {
        if (page != null) {
            this.limit = size;
            if (size != null) {
                this.offset = page * size;
            }
        }

        if (sort != null) {
            if (sort.startsWith("-")) {
                this.sortBy = sort.substring(1);
                this.sortAscending = false;
            } else {
                this.sortBy = sort;
                this.sortAscending = true;
            }
        }
    }
}
