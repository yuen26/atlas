package org.atlas.shared.context;

import lombok.Data;
import org.atlas.business.user.domain.shared.enums.Role;

@Data
public class UserInfo {

    private Integer userId;
    private Role role;
}
