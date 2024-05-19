package com.boxing.shop.react.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;


@Data
@Entity
@Table(
        name = "orders",
        indexes = {
                @Index(name = "order_id",
                        columnList = "order_id",
                        unique = true)
        }
)
public class Order {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            type = org.hibernate.id.enhanced.SequenceStyleGenerator.class,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "order_id", unique = true , nullable = false)
    private Long orderId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="order_id", referencedColumnName = "order_id")
    private List<OrderProduct> products;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;


}
