package org.atlas.business.user.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.business.user.application.contract.model.LoginUserDto;
import org.atlas.framework.command.contract.Command;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLoginUserCommand implements Command<LoginUserDto> {

    private String email;
}
