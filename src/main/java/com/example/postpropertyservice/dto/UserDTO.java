package com.example.postpropertyservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
}
