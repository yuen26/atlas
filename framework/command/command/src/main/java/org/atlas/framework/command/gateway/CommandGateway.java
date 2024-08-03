package org.atlas.framework.command.gateway;

import org.atlas.framework.command.contract.Command;

import java.util.concurrent.CompletableFuture;

public interface CommandGateway {

    <C extends Command<R>, R> R send(C command) throws Exception;

    <C extends Command<R>, R> CompletableFuture<R> sendAsync(C command) throws Exception;
}
