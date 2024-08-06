package org.atlas.framework.rest.server.core.context;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.atlas.business.user.domain.shared.enums.Role;
import org.atlas.shared.constant.CustomHeaders;
import org.atlas.shared.context.UserContext;
import org.atlas.shared.context.UserInfo;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class UserContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String userId = request.getHeader(CustomHeaders.USER_ID);
        String userRole = request.getHeader(CustomHeaders.USER_ROLE);

        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userRole)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(Integer.valueOf(userId));
            userInfo.setRole(Role.valueOf(userRole));
            UserContext.setCurrentUser(userInfo);
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }
}
