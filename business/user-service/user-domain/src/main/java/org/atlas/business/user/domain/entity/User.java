package org.atlas.business.user.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.business.user.domain.shared.enums.Role;
import org.atlas.commons.model.AuditableEntity;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class User extends AuditableEntity implements Serializable {

    @EqualsAndHashCode.Include
    private Integer id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;
    private BigDecimal credit;
}
