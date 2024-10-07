package org.atlas.framework.job.quartz.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.job.application.contract.command.StatsOrderCommand;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatsOrderJob implements Job {

    private final CommandGateway commandGateway;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            commandGateway.send(new StatsOrderCommand());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
