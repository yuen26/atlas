package org.atlas.framework.rest.server.core.response;

import lombok.Data;

@Data
public class RestResponse<T> {

    public static final int DEFAULT_SUCCESS_CODE = 200;
    public static final String DEFAULT_SUCCESS_MESSAGE = "success";

    private int code;
    private T data;
    private String message;

    public static <T> RestResponse<T> success(T data) {
        RestResponse<T> instance = new RestResponse<>();
        instance.setCode(DEFAULT_SUCCESS_CODE);
        instance.setData(data);
        instance.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return instance;
    }

    public static RestResponse<Void> error(int code, String message) {
        RestResponse<Void> instance = new RestResponse<>();
        instance.setCode(code);
        instance.setMessage(message);
        return instance;
    }
}
