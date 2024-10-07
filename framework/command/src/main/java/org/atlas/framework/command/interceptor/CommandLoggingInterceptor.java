package org.atlas.framework.command.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.atlas.commons.context.CurrentUser;
import org.atlas.commons.context.UserContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandLoggingInterceptor implements CommandInterceptor {

    @Override
    public void preHandle(Object command) {
        CurrentUser currentUser = UserContext.getCurrentUser();
        if (currentUser == null) {
            log.info("Anonymous user executed command {}", command);
        } else {
            log.info("User {} executed command {}", currentUser, command);
        }
    }

    @Override
    public void postHandle(Object command) {
        // Ignored
    }
}
