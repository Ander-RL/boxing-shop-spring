package com.boxing.shop.react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderDto {

    private Long id;

    private Long idCustomer;

    private Long idProduct;

    private Long idOrder;

    private Integer quantity;

}
