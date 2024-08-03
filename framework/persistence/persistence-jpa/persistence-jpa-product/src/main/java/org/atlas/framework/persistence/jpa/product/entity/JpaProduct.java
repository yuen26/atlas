package org.atlas.framework.persistence.jpa.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private JpaCategory category;

    private BigDecimal price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private Boolean featured;
}
