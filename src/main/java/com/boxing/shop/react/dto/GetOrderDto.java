package com.boxing.shop.react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderDto {

    private Long orderId;

    private Long customerId;

    private List<GetOrderProductDto> products;

    private Double totalAmount;

}
