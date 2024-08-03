package org.atlas.framework.search.elasticsearch.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;

@Document(indexName = "product")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EsProduct {

    @Id
    @Field(type = FieldType.Integer)
    @EqualsAndHashCode.Include
    private Integer id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Integer)
    private Integer quantity;

    @Field(type = FieldType.Boolean)
    private Boolean featured;

    @Field(type = FieldType.Keyword)
    private ProductStatus status;

    @Field(type = FieldType.Nested, includeInParent = true)
    private EsCategory category;

    @Field(type = FieldType.Date)
    private Date createdAt;
}
