package org.atlas.framework.rest.client.apachehttpclient.core;

import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.atlas.commons.constant.CustomHeaders;
import org.atlas.commons.context.CurrentUser;
import org.atlas.commons.context.UserContext;

import java.io.IOException;

public class UserContextRequestInterceptor implements HttpRequestInterceptor {

    @Override
    public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) throws HttpException, IOException {
        CurrentUser currentUser = UserContext.getCurrentUser();
        if (currentUser != null) {
            httpRequest.addHeader(CustomHeaders.USER_ID, currentUser.getUserId());
            httpRequest.addHeader(CustomHeaders.USER_ROLE, currentUser.getRole());
        }
    }
}
