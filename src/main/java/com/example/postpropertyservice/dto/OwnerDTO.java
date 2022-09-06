package com.example.postpropertyservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDTO {
    private int ownerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
}
