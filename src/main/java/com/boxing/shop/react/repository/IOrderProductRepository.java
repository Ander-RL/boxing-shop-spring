package com.boxing.shop.react.repository;

import com.boxing.shop.react.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface IOrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
