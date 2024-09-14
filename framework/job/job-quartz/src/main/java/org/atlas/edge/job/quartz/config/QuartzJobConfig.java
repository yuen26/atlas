package org.atlas.edge.job.quartz.config;

import org.atlas.edge.job.quartz.job.CancelOverduePendingOrdersJob;
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
    public JobDetail cancelOverduePendingOrdersJobDetail() {
        return JobBuilder.newJob()
            .ofType(CancelOverduePendingOrdersJob.class)
            .withIdentity(CancelOverduePendingOrdersJob.class.getSimpleName())
            .storeDurably()
            .build();
    }

    @Bean
    public Trigger cancelOverduePendingOrdersTrigger(JobDetail cancelOverduePendingOrdersJobDetail) {
        return TriggerBuilder.newTrigger()
            .forJob(cancelOverduePendingOrdersJobDetail)
            .withIdentity(CancelOverduePendingOrdersJob.class.getSimpleName())
            // Run every 15 minutes
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0/15 * * * ?"))
            .build();
    }
}
