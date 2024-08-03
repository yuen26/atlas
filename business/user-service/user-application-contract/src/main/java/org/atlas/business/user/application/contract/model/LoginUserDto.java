package org.atlas.business.user.application.contract.model;

import lombok.Data;
import org.atlas.business.user.domain.shared.enums.Role;

@Data
public class LoginUserDto {

    private Integer id;
    private String email;
    private String password;
    private Role role;
}
