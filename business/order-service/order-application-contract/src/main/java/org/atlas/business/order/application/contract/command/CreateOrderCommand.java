package org.atlas.business.order.application.contract.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.atlas.framework.command.contract.Command;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateOrderCommand implements Command<Integer> {

    @Data
    public static class OrderItem {

        @NotNull
        private Integer productId;

        @NotNull
        @Min(0)
        private Integer quantity;
    }

    @NotEmpty
    private List<@Valid OrderItem> orderItems;

    public void addItem(OrderItem orderItem) {
        if (this.orderItems == null) {
            this.orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
    }
}
