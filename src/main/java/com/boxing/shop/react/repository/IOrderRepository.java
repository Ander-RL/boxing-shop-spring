package com.boxing.shop.react.repository;

import com.boxing.shop.react.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Override
    Optional<Order> findById(Long id);

    @Override
    List<Order> findAll();
}
