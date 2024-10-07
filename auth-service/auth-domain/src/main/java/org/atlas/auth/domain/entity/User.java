package org.atlas.auth.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.auth.domain.shared.enums.Role;
import org.atlas.commons.model.AuditableEntity;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class User extends AuditableEntity implements Serializable {

    @EqualsAndHashCode.Include
    private Integer id;
    private String username;
    private String password;
    private Role role;
}
