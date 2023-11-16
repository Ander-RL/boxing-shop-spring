package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.dto.GetSelectedProductsDto;
import com.boxing.shop.react.entity.Product;
import com.boxing.shop.react.mapper.IProductMapper;
import com.boxing.shop.react.repository.IProductRepository;
import lombok.AllArgsConstructor;
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
     * Returns selected products
     * @return GetSelectedProductsDto
     */
    @Transactional(readOnly = true)
    public GetSelectedProductsDto getSelectedProducts(List<String> selectedProducts){
        List<GetProductDto> productList = new ArrayList<>();

        for (String selectedProduct : selectedProducts) {
            GetProductDto product =  productMapper.entityToDto(productRepository.findByKeyWord(selectedProduct));
            productList.add(product);
        }
        GetSelectedProductsDto selection = new GetSelectedProductsDto();
        selection.setSelectedProducts(productList);

        return selection;
    }
}
