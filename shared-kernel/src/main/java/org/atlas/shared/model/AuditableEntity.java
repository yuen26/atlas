package org.atlas.shared.model;

import lombok.Data;

import java.util.Date;

@Data
public class AuditableEntity {

    protected Date createdAt;
    protected Date updatedAt;
}
