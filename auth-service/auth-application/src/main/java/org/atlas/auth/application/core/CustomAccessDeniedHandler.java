package org.atlas.auth.application.core;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * AccessDecisionHandler is triggered when a user is authenticated but not authorized to access the given resource.
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
            throws IOException, ServletException {
        if (response.isCommitted()) {
            log.info("Response has already been committed");
            return;
        }
        log.error("Access denied exception", exception);
        HttpUtil.sendError(response, "Access denied", HttpStatus.FORBIDDEN);
    }
}
