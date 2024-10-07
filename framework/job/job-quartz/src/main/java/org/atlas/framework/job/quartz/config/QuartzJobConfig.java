package org.atlas.framework.job.quartz.config;

import org.atlas.framework.job.quartz.job.StatsOrderJob;
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
    public JobDetail statsOrderJobDetail() {
        return JobBuilder.newJob()
            .ofType(StatsOrderJob.class)
            .withIdentity(StatsOrderJob.class.getSimpleName())
            .storeDurably()
            .build();
    }

    @Bean
    public Trigger statsOrderTrigger(JobDetail statsOrderJobDetail) {
        return TriggerBuilder.newTrigger()
            .forJob(statsOrderJobDetail)
            .withIdentity(StatsOrderJob.class.getSimpleName())
            // Run at midnight
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?"))
            .build();
    }
}
