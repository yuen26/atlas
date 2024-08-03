package org.atlas.framework.rest.server.core.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

@RestControllerAdvice
public class RestResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof RestResponse<?>) {
            return body;
        }

        // Ignore Spring default error response
        if (body instanceof Map<?,?>) {
            Map<String, Object> map = (Map<String, Object>) body;
            if (map.containsKey("timestamp") &&
                map.containsKey("status") &&
                map.containsKey("error") &&
                map.containsKey("path")) {
                return body;
            }
        }

        return RestResponse.success(body);
    }
}
