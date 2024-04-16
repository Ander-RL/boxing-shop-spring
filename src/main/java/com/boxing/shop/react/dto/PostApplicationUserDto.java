package com.boxing.shop.react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostApplicationUserDto {

    private String username;

    private String password;
}
