package com.boxing.shop.react.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(
        name = "product_line",
        indexes = {
                @Index(name = "product_id",
                        columnList = "product_id",
                        unique = true)
        }
)
public class ProductLine {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            type = org.hibernate.id.enhanced.SequenceStyleGenerator.class,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "product_line_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "product_id", unique = true , nullable = false)
    private String productId;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "description", nullable = false)
    private String description;


}
