package org.atlas.framework.grpc.server.core;

import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import org.atlas.commons.exception.BusinessException;

@GrpcAdvice
@Slf4j
public class GrpcExceptionHandler {

    @net.devh.boot.grpc.server.advice.GrpcExceptionHandler
    public Status handleException(Throwable e) {
        log.error("Occurred an exception", e);
        if (e instanceof BusinessException) {
            return Status.INTERNAL
                .withDescription(e.getMessage())
                .withCause(e);
        } else {
            return Status.INTERNAL
                .withDescription("An internal server error occurred")
                .withCause(e);
        }
    }
}
