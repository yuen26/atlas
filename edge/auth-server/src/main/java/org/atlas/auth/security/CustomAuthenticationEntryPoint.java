package org.atlas.auth.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * AuthenticationEntryPoint is triggered when an unauthenticated user requests a secured HTTP resource.
 */
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        if (response.isCommitted()) {
            log.info("Response has already been committed");
            return;
        }
        log.error("Unauthenticated exception", exception);
        HttpUtil.sendError(response, "Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
