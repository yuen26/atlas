package org.atlas.commons.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppError {

    DEFAULT(1000, "error.common.default"),
    UNAVAILABLE_SERVICE(1001, "error.common.unavailable_service"),
    BAD_REQUEST(1002, "error.common.bad_request"),
    PERMISSION_DENIED(1003, "error.common.permission_denied"),

    CUSTOMER_NOT_FOUND(2000, "error.customer.not_found"),

    PRODUCT_NOT_FOUND(3000, "error.product.not_found"),

    ORDER_NOT_FOUND(4000, "error.order.not_found"),
    ORDER_INVALID_STATUS(4001, "error.order.invalid_status"),
    ;

    private final int errorCode;
    private final String messageCode;

    @Override
    public String toString() {
        return String.format("%d %s", errorCode, name());
    }
}
