package com.boxing.shop.react.controller;

import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Return list of products
     * @return List<GetProductDto>
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetProductDto> getProducts(){

        return productService.getProducts();
    }

    /**
     * Return the product by id
     * @param productId productId
     * @return GetProductDto
     */
    @GetMapping(path = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetProductDto getProduct(@PathVariable Long productId){

        return productService.getProduct(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Return list of selected products
     * @return List<GetProductDto>
     */
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/selectedProducts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetProductDto> getSelectedProducts(@RequestBody List<String> selectedProducts){

        return productService.getSelectedProducts(selectedProducts);
    }
}
