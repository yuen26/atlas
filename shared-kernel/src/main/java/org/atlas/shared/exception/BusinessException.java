package org.atlas.shared.exception;

public class BusinessException extends RuntimeException {

    private final int errorCode;
    private final String messageCode;

    public BusinessException(AppError error) {
        super(error.toString());
        this.errorCode = error.getErrorCode();
        this.messageCode = error.getMessageCode();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
