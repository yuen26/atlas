package org.atlas.business.user.application.contract.model;

import lombok.Data;

@Data
public class CustomerDto {

    private Integer id;
    private String username;
    private String email;
}
