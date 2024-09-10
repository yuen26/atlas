package org.atlas.commons.model;

import lombok.Data;

import java.util.Date;

@Data
public class AuditableEntity {

    protected Date createdAt;
    protected Date updatedAt;
}
