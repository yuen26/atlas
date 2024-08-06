package org.atlas.framework.rest.client.feign.core;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.atlas.shared.constant.CustomHeaders;
import org.atlas.shared.context.UserContext;
import org.atlas.shared.context.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserContextRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        UserInfo userInfo = UserContext.getCurrentUser();
        requestTemplate.header(CustomHeaders.USER_ID, String.valueOf(userInfo.getUserId()));
        requestTemplate.header(CustomHeaders.USER_ROLE, userInfo.getRole().name());
    }
}
