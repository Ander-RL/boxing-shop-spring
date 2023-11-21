package com.boxing.shop.react.repository;

import com.boxing.shop.react.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

    @Query(value = "SELECT * FROM PRODUCT WHERE KEYWORD LIKE ?1", nativeQuery = true)
    List<Product> findByKeyWord(String keyword);
}
