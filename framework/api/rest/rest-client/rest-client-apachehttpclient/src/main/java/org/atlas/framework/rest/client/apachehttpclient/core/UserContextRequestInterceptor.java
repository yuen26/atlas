package org.atlas.framework.rest.client.apachehttpclient.core;

import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.atlas.shared.constant.CustomHeaders;
import org.atlas.shared.context.UserContext;
import org.atlas.shared.context.UserInfo;

import java.io.IOException;

public class UserContextRequestInterceptor implements HttpRequestInterceptor {

    @Override
    public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) throws HttpException, IOException {
        UserInfo userInfo = UserContext.getCurrentUser();
        httpRequest.addHeader(CustomHeaders.USER_ID, userInfo.getUserId());
        httpRequest.addHeader(CustomHeaders.USER_ROLE, userInfo.getRole());
    }
}
