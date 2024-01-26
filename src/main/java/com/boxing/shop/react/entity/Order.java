package com.boxing.shop.react.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(
        name = "orders",
        indexes = {
                @Index(name = "order_id",
                        columnList = "id",
                        unique = true)
        }
)
public class Order {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true , nullable = false)
    private Long id;

    @Column(name = "id_customer", nullable = false)
    private Long idCustomer;

    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;


}
