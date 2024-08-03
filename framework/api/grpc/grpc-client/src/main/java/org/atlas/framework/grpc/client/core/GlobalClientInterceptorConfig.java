package org.atlas.framework.grpc.client.core;

import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GlobalClientInterceptorConfig {

    @GrpcGlobalClientInterceptor
    public LogGrpcInterceptor logClientInterceptor() {
        return new LogGrpcInterceptor();
    }
}
