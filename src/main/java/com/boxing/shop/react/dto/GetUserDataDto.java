package com.boxing.shop.react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDataDto {

    private String username;

    private String email;

    private String firstName;

    private String secondName;

    private String birthDate;

    private List<GetOrderDto> orders;

    private List<GetAddressDto> addressList;

}
