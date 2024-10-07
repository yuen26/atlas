package org.atlas.auth.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.auth.application.contract.command.LogoutCommand;
import org.atlas.auth.application.core.TokenService;
import org.atlas.framework.command.executor.CommandExecutor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogoutCommandExecutor implements CommandExecutor<LogoutCommand, Void> {

    private final TokenService tokenService;

    @Override
    public Void execute(LogoutCommand command) throws Exception {
        tokenService.revokeToken(command.getAuthorizationHeader());
        return null;
    }
}
