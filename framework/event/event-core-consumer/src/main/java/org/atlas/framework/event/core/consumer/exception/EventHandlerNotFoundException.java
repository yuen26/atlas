package org.atlas.framework.event.core.consumer.exception;

public class EventHandlerNotFoundException extends RuntimeException {

    public EventHandlerNotFoundException(String message) {
        super(message);
    }
}
