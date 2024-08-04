package org.atlas.framework.job.spring.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.order.application.contract.command.CancelOverduePendingOrdersCommand;
import org.atlas.framework.command.gateway.CommandGateway;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CancelOverduePendingOrdersJob {

    private final CommandGateway commandGateway;

    /**
     * Run every 15 minutes
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    public void execute() {
        try {
            CancelOverduePendingOrdersCommand command = new CancelOverduePendingOrdersCommand();
            commandGateway.send(command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
