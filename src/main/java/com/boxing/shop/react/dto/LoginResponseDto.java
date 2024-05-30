package com.boxing.shop.react.dto;

import com.boxing.shop.react.entity.ApplicationUser;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseDto {
    //private ApplicationUser user;
    private String username;
    private String firstName;
    private String secondName;
    private String email;
    private String message;
    private int statusCode;
    private String jwt;
}
