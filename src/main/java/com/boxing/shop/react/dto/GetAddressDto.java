package com.boxing.shop.react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAddressDto {

    private String door;

    private String floor;

    private String street;

    private String city;

    private String country;

    private String postalCode;

}
