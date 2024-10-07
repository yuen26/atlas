package org.atlas.order.application.contract.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.atlas.framework.command.Command;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlaceOrderCommand implements Command<Integer> {

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

    @NotBlank
    private String address;

    public void addItem(OrderItem orderItem) {
        if (this.orderItems == null) {
            this.orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
    }
}
