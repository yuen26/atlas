package org.atlas.framework.rest.client.contract.common;

import lombok.Data;

@Data
public class Response<T> {

    private boolean success;
    private T data;
    private Integer code;
    private String message;
}
