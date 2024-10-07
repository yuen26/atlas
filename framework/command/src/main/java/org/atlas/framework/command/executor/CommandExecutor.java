package org.atlas.framework.command.executor;

import org.atlas.framework.command.Command;

public interface CommandExecutor<C extends Command<R>, R> {

    R execute(C command) throws Exception;
}
