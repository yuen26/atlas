package org.atlas.edge.job.quartz.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.edge.job.core.CancelOverduePendingOrdersJobLogic;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CancelOverduePendingOrdersJob implements Job {

    private final CancelOverduePendingOrdersJobLogic jobLogic;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        jobLogic.execute();
    }
}