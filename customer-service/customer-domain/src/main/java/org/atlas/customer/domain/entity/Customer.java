package org.atlas.customer.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.commons.model.AuditableEntity;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Customer extends AuditableEntity implements Serializable {

    @EqualsAndHashCode.Include
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private BigDecimal credit;
}
