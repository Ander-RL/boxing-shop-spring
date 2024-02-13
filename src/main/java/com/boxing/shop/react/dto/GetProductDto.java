package com.boxing.shop.react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductDto {

    private Long productId;

    private String keyword;

    private String name;

    private String img;

    private Integer quantity;

    private Double unitaryAmount;

    private String description;

}
