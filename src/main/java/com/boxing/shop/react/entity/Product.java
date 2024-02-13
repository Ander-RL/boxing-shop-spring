package com.boxing.shop.react.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(
        name = "products",
        indexes = {
                @Index(name = "product_id",
                        columnList = "product_id",
                        unique = true)
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id", unique = true , nullable = false)
    private Long productId;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unitary_amount", nullable = false)
    private Double unitaryAmount;

    @Column(name = "description", nullable = false)
    private String description;


}
