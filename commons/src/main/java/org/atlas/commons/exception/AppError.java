package org.atlas.commons.exception;

public enum AppError {

    DEFAULT(10000, "error.common.default"),
    UNAVAILABLE_SERVICE(10001, "error.common.unavailable_service"),
    BAD_REQUEST(10002, "error.common.bad_request"),

    ORDER_NOT_FOUND(20000, "error.order.not_found"),
    ORDER_INVALID_STATUS(20000, "error.order.invalid_status"),

    PRODUCT_NOT_FOUND(30000, "error.product.not_found"),

    USER_NOT_FOUND(30000, "error.user.not_found"),
    USER_EMAIL_EXISTS(30000, "error.user.email_exists"),
    ;

    private final int errorCode;
    private final String messageCode;

    AppError(int errorCode, String messageCode) {
        this.errorCode = errorCode;
        this.messageCode = messageCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public String toString() {
        return String.format("%d %s", errorCode, name());
    }
}
