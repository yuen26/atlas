package org.atlas.business.user.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.business.user.application.contract.model.UserAuthDto;
import org.atlas.framework.command.contract.Command;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserAuthCommand implements Command<UserAuthDto> {

    private String email;
}
