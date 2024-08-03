package org.atlas.framework.event.snsfanout.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.core.publisher.EventPublisher;
import org.atlas.shared.util.json.JsonUtil;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class SnsFanoutEventPublisher implements EventPublisher {

    private final SnsClient snsClient;
    private final SnsFanoutEventPublisherConfig.TopicProperties topicsProperties;

    @Override
    public void publish(DomainEvent event) {
        String topicArn = topicsProperties.getTopic(event);
        if (topicArn == null) {
            log.error("No topic configured for event type: {}", event.type());
            return;
        }

        String message = JsonUtil.toJson(event);
        PublishRequest request = PublishRequest.builder()
            .message(message)
            .topicArn(topicArn)
            .build();
        PublishResponse response = snsClient.publish(request);
        log.info("Published event: {}\nStatus: {}. Response: {}",
            event, response.sdkHttpResponse().statusCode(), response);
    }
}
