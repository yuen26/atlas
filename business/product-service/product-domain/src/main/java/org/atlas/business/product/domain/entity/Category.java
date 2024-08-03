package org.atlas.business.product.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.shared.model.AuditableEntity;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Category extends AuditableEntity implements Serializable {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;

    public Category(Integer id) {
        this.id = id;
    }
}
