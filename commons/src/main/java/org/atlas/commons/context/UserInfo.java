package org.atlas.commons.context;

import lombok.Data;
import org.atlas.business.user.domain.shared.enums.Role;

@Data
public class UserInfo {

    private Integer userId;
    private Role role;

    public boolean isAdmin() {
        return Role.ADMIN.equals(role);
    }

    public boolean isCustomer() {
        return Role.CUSTOMER.equals(role);
    }
}
