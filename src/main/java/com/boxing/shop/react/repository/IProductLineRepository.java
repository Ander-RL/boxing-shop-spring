package com.boxing.shop.react.repository;

import com.boxing.shop.react.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
public interface IProductLineRepository extends JpaRepository<ProductLine, String> {

    @Override
    Optional<ProductLine> findById(String id);

    @Override
    List<ProductLine> findAll();

    @Query(value = "SELECT * FROM PRODUCT_LINE WHERE KEYWORD LIKE ?1", nativeQuery = true)
    List<ProductLine> findByKeyWord(String keyword);
}
