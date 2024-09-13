package org.atlas.edge.auth.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.atlas.commons.utils.json.JsonUtil;
import org.atlas.framework.rest.server.core.response.RestResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtil {

    public static void sendError(HttpServletResponse response, String errorMessage, HttpStatus httpStatus) throws IOException {
        RestResponse<Void> responseObj = RestResponse.error(httpStatus.value(), errorMessage);
        String responseBody = JsonUtil.toJson(responseObj);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(httpStatus.value());
        response.getWriter().write(responseBody);
    }
}
