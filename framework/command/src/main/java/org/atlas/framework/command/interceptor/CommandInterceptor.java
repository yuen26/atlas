package org.atlas.framework.command.interceptor;

public interface CommandInterceptor {

    void preHandle(Object command);
    void postHandle(Object command);
}
