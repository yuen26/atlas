package org.atlas.framework.event.core.outbox;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ConditionalOnProperty(value = "app.outbox.enabled", havingValue = "true")
@EntityScan(basePackages = "org.atlas.framework.event.core.publisher.outbox")
@EnableJpaRepositories(basePackages = "org.atlas.framework.event.core.publisher.outbox")
@EnableScheduling
public class OutboxConfig {
}
