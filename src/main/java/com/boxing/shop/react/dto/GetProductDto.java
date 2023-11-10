package com.boxing.shop.react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductDto {

    private Long id;

    private String name;

    private String img;

    private Integer quantity;

    private Double price;

    private Double totalPrice;

    private String description;

}
