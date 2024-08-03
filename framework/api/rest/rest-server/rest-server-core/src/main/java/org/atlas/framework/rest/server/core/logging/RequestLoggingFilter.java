package org.atlas.framework.rest.server.core.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.rest.server.core.idempotence.IdempotenceKeyGenerator;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final String IDEMPOTENCE_KEY = "x-request-id";
    private static final int MAX_PAYLOAD_LENGTH = 1024 * 1024; // 1 MB in bytes

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String idempotenceKey = IdempotenceKeyGenerator.generate();

        // Support the next requests to be able to retrieve the idempotence key
        request.setAttribute(IDEMPOTENCE_KEY, idempotenceKey);

        // Log idempotence key. Need to put it into log pattern. See logback-spring.xml file.
        // Note: MDC does not work with asynchronous scenarios.
        MDC.put(IDEMPOTENCE_KEY, idempotenceKey);
        HttpServletRequestUtil.logRequest(log, request, MAX_PAYLOAD_LENGTH);

        filterChain.doFilter(request, response);
    }
}
