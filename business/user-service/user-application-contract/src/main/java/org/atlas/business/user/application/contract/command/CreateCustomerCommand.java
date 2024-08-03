package org.atlas.business.user.application.contract.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.atlas.framework.command.contract.Command;
import org.atlas.shared.constant.Patterns;

@Data
public class CreateCustomerCommand implements Command<Integer> {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = Patterns.PASSWORD, message = "{error.user.invalid_password}")
    private String password;
}
