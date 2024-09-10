package org.atlas.framework.rest.server.core.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.i18n.service.MessageService;
import org.atlas.framework.rest.server.core.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse<Void> handle(BusinessException e) {
        log.error("Occurred a business exception", e);
        return RestResponse.error(e.getErrorCode(), messageService.getMessage(e.getMessageCode()));
    }

    /**
     * Invalid request body
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<Void> handle(MethodArgumentNotValidException e) {
        FieldError firstFieldError = e.getBindingResult().getFieldErrors().get(0);
        String message = String.format("[%s] %s", firstFieldError.getField(), firstFieldError.getDefaultMessage());
        return RestResponse.error(AppError.BAD_REQUEST.getErrorCode(), message);
    }

    /**
     * Missing a required request parameter
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<Void> handle(MissingServletRequestParameterException e) {
        return RestResponse.error(AppError.BAD_REQUEST.getErrorCode(), "Missing " + e.getParameterName());
    }
}
