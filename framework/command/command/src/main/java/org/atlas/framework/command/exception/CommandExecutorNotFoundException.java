package org.atlas.framework.command.exception;

public class CommandExecutorNotFoundException extends RuntimeException {

    public CommandExecutorNotFoundException(String message) {
        super(message);
    }
}
