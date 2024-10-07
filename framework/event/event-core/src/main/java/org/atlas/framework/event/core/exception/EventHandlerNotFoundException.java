package org.atlas.framework.event.core.exception;

public class EventHandlerNotFoundException extends RuntimeException {

    public EventHandlerNotFoundException(String message) {
        super(message);
    }
}
