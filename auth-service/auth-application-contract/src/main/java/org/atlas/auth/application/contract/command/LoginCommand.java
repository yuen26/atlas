package org.atlas.auth.application.contract.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.atlas.auth.application.contract.model.LoginResponse;
import org.atlas.framework.command.Command;

@Data
public class LoginCommand implements Command<LoginResponse> {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
