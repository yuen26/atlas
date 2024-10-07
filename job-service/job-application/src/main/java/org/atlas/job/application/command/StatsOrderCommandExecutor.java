package org.atlas.job.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.command.executor.CommandExecutor;
import org.atlas.job.application.contract.command.StatsOrderCommand;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatsOrderCommandExecutor implements CommandExecutor<StatsOrderCommand, Void> {

    @Override
    public Void execute(StatsOrderCommand command) throws Exception {
        return null;
    }
}
