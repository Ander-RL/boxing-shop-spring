package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.mapper.IProductMapper;
import com.boxing.shop.react.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;

    private final IProductMapper productMapper;

    /**
     * Return list of products
     * @return
     */
    @Transactional(readOnly = true)
    public List<GetProductDto> getProducts(){

        return productRepository.findAll()
                .stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Return the product by id
     * @param productId
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<GetProductDto> getProduct(Long productId){

        return productRepository.findById(productId)
                .map(productMapper::entityToDto);
    }
}
