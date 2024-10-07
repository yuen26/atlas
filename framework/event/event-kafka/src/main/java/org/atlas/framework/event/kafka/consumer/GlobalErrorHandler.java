package org.atlas.framework.event.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

@Slf4j
public class GlobalErrorHandler implements CommonErrorHandler {

    @Override
    public boolean handleOne(Exception thrownException,
                             ConsumerRecord<?, ?> record,
                             Consumer<?, ?> consumer,
                             MessageListenerContainer container) {
        log.error("Occurred exception while processing record: payload={}, partition={}, offset={}, cause={}",
                record.value(), record.partition(), record.offset(), thrownException.getCause().getMessage());
        return true;
    }
}
