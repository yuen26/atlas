package org.atlas.business.user.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.business.user.application.contract.model.UserDto;
import org.atlas.framework.command.contract.Command;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserCommand implements Command<UserDto> {

    private Integer id;
}
