package org.atlas.customer.application.contract.model;

import lombok.Data;

@Data
public class CustomerDto {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
