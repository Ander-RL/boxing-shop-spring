package com.boxing.shop.react.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(
        name = "order_products",
        indexes = {
                @Index(name = "order_product_id",
                        columnList = "order_product_id",
                        unique = true)
        }
)
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_product_id", nullable = false)
    private Long orderProductId;

    @Column(name = "purchased_product", nullable = false)
    private Long purchasedProduct;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;


}
