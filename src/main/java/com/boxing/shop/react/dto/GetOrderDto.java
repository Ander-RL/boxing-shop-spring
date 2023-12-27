package com.boxing.shop.react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderDto {

    private Long idOrder;

    private Long idCustomer;

    private Long idProduct;

    private Integer quantity;

}
