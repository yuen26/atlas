package org.atlas.framework.command.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.atlas.framework.command.contract.Command;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.framework.command.exception.CommandExecutorNotFoundException;
import org.atlas.framework.command.interceptor.CommandInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultCommandGateway implements CommandGateway {

    private final Map<String, CommandExecutor<?, ?>> executorMap;
    private final List<CommandInterceptor> interceptors;

    @Override
    public <C extends Command<R>, R> R send(C command) throws Exception {
        CommandExecutor<C, R> executor = obtainExecutor(command);
        interceptors.forEach(interceptor -> interceptor.preHandle(command));
        R result = executor.execute(command);
        interceptors.forEach(interceptor -> interceptor.postHandle(command));
        return result;
    }

    @Override
    @Async("asyncCommandExecutor")
    public <C extends Command<R>, R> CompletableFuture<R> sendAsync(C command) throws Exception {
        R result = send(command);
        return CompletableFuture.completedFuture(result);
    }

    @SuppressWarnings("unchecked")
    private <C extends Command<R>, R> CommandExecutor<C, R> obtainExecutor(Command<R> command) {
        String executorBeanName = StringUtils.uncapitalize(command.getClass().getSimpleName()) + "Executor";
        CommandExecutor<?, ?> executor = executorMap.get(executorBeanName);
        if (executor == null) {
            throw new CommandExecutorNotFoundException("Not found executor for command " + command.getClass());
        }
        return (CommandExecutor<C, R>) executor;
    }
}
