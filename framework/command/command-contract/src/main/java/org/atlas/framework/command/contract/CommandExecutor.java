package org.atlas.framework.command.contract;

public interface CommandExecutor<C extends Command<R>, R> {

    R execute(C command) throws Exception;
}
