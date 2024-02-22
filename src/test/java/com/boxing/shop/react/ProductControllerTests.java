package com.boxing.shop.react;

import com.boxing.shop.react.constants.Constants;
import com.boxing.shop.react.controller.ProductController;
import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.entity.Product;
import com.boxing.shop.react.mapper.IProductMapper;
import com.boxing.shop.react.repository.IProductRepository;
import com.boxing.shop.react.service.ProductService;
import com.boxing.shop.react.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductControllerTests {

	@Mock
	private IProductRepository productRepository;
	@Mock
	private ProductService productService;

	@Autowired
	private ProductController productController;

	@Autowired
	private IProductMapper productMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void getProductsControllerTest() {
		Mockito.when(productRepository.findAll()).thenReturn(preprareProductList());
		List<GetProductDto> productList = productController.getProducts();
		List<Object> expectedProductList = TestUtil.leerJSONasDTOList(Constants.GetProductDtoListPath, GetProductDto.class);

		Assertions.assertEquals(productList, expectedProductList);
	}

	@Test
	void getProductByIdControllerTest() {
		Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable((Product) TestUtil.leerJSONasDTO(Constants.ProductPath, Product.class)));
		GetProductDto product = productController.getProduct(1L);
		GetProductDto expectedProduct = (GetProductDto) TestUtil.leerJSONasDTO(Constants.ProductPath, GetProductDto.class);

		Assertions.assertEquals(product, expectedProduct);
	}

	@Test
	void getSelectedProductsControllerTest() {
		List<GetProductDto> productList = productController.getSelectedProducts(
				(Objects.requireNonNull(TestUtil.leerJSONasDTOList(Constants.SelectedProductListPath, String.class))
				).stream().map(Object::toString).toList());
		List<Object> expectedProductList = TestUtil.leerJSONasDTOList(Constants.ResponseSelectedProductListPath, GetProductDto.class);

		Assertions.assertEquals(productList, expectedProductList);
	}

	@Test
	void getSelectedProductsByIdControllerTest() {
		List<GetProductDto> productList = productController.getSelectedProductsById(
				(Objects.requireNonNull(TestUtil.leerJSONasDTOList(Constants.SelectedProductByIdListPath, String.class))
				).stream().map(Object::toString).toList());
		List<Object> expectedProductList = TestUtil.leerJSONasDTOList(Constants.ResponseSelectedProductListPath, GetProductDto.class);

		Assertions.assertEquals(productList, expectedProductList);
	}

	private List<Product> preprareProductList() {
		List<Product> entityList = new ArrayList<>();
		List<Object> productDtoList = TestUtil.leerJSONasDTOList(Constants.GetProductDtoListPath, GetProductDto.class);

		for(Object productDto : productDtoList){
			entityList.add(productMapper.getProductDtoToEntity((GetProductDto) productDto));
		}

		return entityList;
	}

}
