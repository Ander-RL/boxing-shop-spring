package com.boxing.shop.react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostRegistrationUserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String firstName;
    private String secondName;
    private String birthDate;
}
