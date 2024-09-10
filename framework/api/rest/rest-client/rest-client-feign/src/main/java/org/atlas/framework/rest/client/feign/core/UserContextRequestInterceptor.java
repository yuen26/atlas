package org.atlas.framework.rest.client.feign.core;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.atlas.commons.constant.CustomHeaders;
import org.atlas.commons.context.UserContext;
import org.atlas.commons.context.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserContextRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        UserInfo userInfo = UserContext.getCurrentUser();
        if (userInfo != null) {
            requestTemplate.header(CustomHeaders.USER_ID, String.valueOf(userInfo.getUserId()));
            requestTemplate.header(CustomHeaders.USER_ROLE, userInfo.getRole().name());
        }
    }
}
