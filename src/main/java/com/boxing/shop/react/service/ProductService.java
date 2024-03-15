package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.entity.Product;
import com.boxing.shop.react.mapper.IProductMapper;
import com.boxing.shop.react.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
     * @return List<GetProductDto>
     */
    @Transactional(readOnly = true)
    public List<GetProductDto> getProducts(){

        return productRepository.findAll()
                .stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Return list of products
     * @return List<GetProductDto>
     */
    @Transactional(readOnly = true)
    public Page<GetProductDto> getProductsPage(Pageable pageRequest){

        return productRepository.findAll(pageRequest).map(productMapper::entityToDto);
    }

    /**
     * Return the product by id
     * @param productId productId
     * @return Optional<GetProductDto>
     */
    @Transactional(readOnly = true)
    public Optional<GetProductDto> getProduct(Long productId){

        return productRepository.findById(productId)
                .map(productMapper::entityToDto);
    }

    /**
     * Returns selected products by id
     * @return List<GetProductDto>
     */
    @Transactional(readOnly = true)
    public List<GetProductDto> getSelectedProductsById(List<String> selectedProductsId){
        List<GetProductDto> productList = new ArrayList<>();

        for (String selectedId : selectedProductsId) {
            Optional<Product> product = productRepository.findById(Long.parseLong(selectedId));
            product.ifPresent(value -> productList.add(productMapper.entityToDto(value)));
        }

        return productList;
    }

    /**
     * Returns selected products
     * @return List<GetProductDto>
     */
    @Transactional(readOnly = true)
    public List<GetProductDto> getSelectedProducts(List<String> selectedProducts){
        List<GetProductDto> productList = new ArrayList<>();

        for (String selectedProduct : selectedProducts) {
            List<Product> products = productRepository.findByKeyWord(selectedProduct);
            products.forEach(product -> productList.add(productMapper.entityToDto(product)));
        }

        return productList;
    }
}
