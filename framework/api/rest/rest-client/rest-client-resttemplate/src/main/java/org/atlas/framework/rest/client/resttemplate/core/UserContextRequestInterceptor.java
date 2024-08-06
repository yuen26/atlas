package org.atlas.framework.rest.client.resttemplate.core;

import org.atlas.shared.constant.CustomHeaders;
import org.atlas.shared.context.UserContext;
import org.atlas.shared.context.UserInfo;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class UserContextRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        UserInfo userInfo = UserContext.getCurrentUser();
        request.getHeaders().add(CustomHeaders.USER_ID, String.valueOf(userInfo.getUserId()));
        request.getHeaders().add(CustomHeaders.USER_ROLE, userInfo.getRole().name());
        return execution.execute(request, body);
    }
}
