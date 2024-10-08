package org.atlas.framework.persistence.jpa.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.framework.persistence.jpa.core.entity.JpaBaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class JpaProduct extends JpaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer quantity;
}
