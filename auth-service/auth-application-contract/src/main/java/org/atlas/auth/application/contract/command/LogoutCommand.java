package org.atlas.auth.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.framework.command.Command;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogoutCommand implements Command<Void> {

    private String authorizationHeader;
}
