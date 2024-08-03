package org.atlas.framework.search.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class EsCategory {

    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Text)
    private String name;
}
