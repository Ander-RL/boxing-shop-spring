package com.boxing.shop.react.controller;

import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/products")
@AllArgsConstructor
@CrossOrigin(origins = "*")
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
     * Return paginated list of products
     * @return List<GetProductDto>
     */
    @GetMapping(path = "/pages",produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<GetProductDto> getProductsByPage(@RequestParam(name = "page",defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "3") int size){

        Pageable pageRequest = PageRequest.of(page, size);
        List<GetProductDto> allProducts = productService.getProducts();

        // starting index
        int start = (int) pageRequest.getOffset();
        // ending index
        int end = Math.min((start + pageRequest.getPageSize()), allProducts.size());
        // sublist
        List<GetProductDto> pageContent = allProducts.subList(start, end);

        return new PageImpl<>(pageContent, pageRequest, allProducts.size());
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
     * Return list of selected products by id
     * @return List<GetProductDto>
     */
    @PostMapping(path = "/selectedProductsById",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetProductDto> getSelectedProductsById(@RequestBody List<String> selectedProducts){

        return productService.getSelectedProductsById(selectedProducts);
    }

    /**
     * Return list of selected products in the filter
     * @return List<GetProductDto>
     */
    @PostMapping(path = "/selectedProducts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetProductDto> getSelectedProducts(@RequestBody List<String> selectedProducts){

        return productService.getSelectedProducts(selectedProducts);
    }
}
