package com.boxing.shop.react.dto;

import com.boxing.shop.react.entity.ApplicationUser;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseDto {
    private ApplicationUser user;
    private String jwt;
}
