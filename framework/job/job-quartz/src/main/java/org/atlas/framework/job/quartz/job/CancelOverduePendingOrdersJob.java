package org.atlas.framework.job.quartz.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.order.application.contract.command.CancelOverduePendingOrdersCommand;
import org.atlas.framework.command.gateway.CommandGateway;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CancelOverduePendingOrdersJob implements Job {

    private final CommandGateway commandGateway;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            CancelOverduePendingOrdersCommand command = new CancelOverduePendingOrdersCommand();
            commandGateway.send(command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
