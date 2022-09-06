package com.example.postpropertyservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
}
