package org.atlas.framework.grpc.server.core;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.atlas.shared.exception.BusinessException;

@GrpcAdvice
public class GrpcExceptionAdvice {

    @GrpcExceptionHandler
    public Status handleException(Throwable e) {
        if (e instanceof BusinessException) {
            return Status.INTERNAL.withDescription(e.getMessage()).withCause(e);
        } else {
            return Status.INTERNAL.withDescription("An internal server error occurred");
        }
    }
}

