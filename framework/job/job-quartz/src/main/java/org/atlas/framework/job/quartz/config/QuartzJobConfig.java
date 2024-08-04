package org.atlas.framework.job.quartz.config;

import org.atlas.framework.job.quartz.job.CancelOverduePendingOrdersJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzJobConfig {

    @Bean
    public JobDetail relayOutboxMessageJobDetail() {
        return JobBuilder.newJob()
            .ofType(CancelOverduePendingOrdersJob.class)
            .withIdentity(CancelOverduePendingOrdersJob.class.getSimpleName())
            .storeDurably()
            .build();
    }

    @Bean
    public Trigger relayOutboxMessageTrigger(JobDetail relayOutboxMessageJobDetail) {
        return TriggerBuilder.newTrigger()
            .forJob(relayOutboxMessageJobDetail)
            .withIdentity(CancelOverduePendingOrdersJob.class.getSimpleName())
            // Run every minute
            .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ?"))
            .build();
    }
}
