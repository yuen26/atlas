package org.atlas.business.user.application.contract.model;

import lombok.Data;
import org.atlas.business.user.domain.shared.enums.Role;

@Data
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private Role role;
}
