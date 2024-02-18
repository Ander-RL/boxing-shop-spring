package com.boxing.shop.react;

import com.boxing.shop.react.controller.ProductController;
import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.entity.Product;
import com.boxing.shop.react.mapper.IProductMapper;
import com.boxing.shop.react.repository.IProductRepository;
import com.boxing.shop.react.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ReactApplicationTests {

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
		List<GetProductDto> expectedProductList = preprareGetProductDtoList();

		Assertions.assertEquals(productList, expectedProductList);
	}

	private List<Product> preprareProductList() {
		List<Product> entityList = new ArrayList<>();
		List<GetProductDto> productDtoList = preprareGetProductDtoList();

		for(GetProductDto productDto : productDtoList){
			entityList.add(productMapper.getProductDtoToEntity(productDto));
		}

		return entityList;
	}

	private List<GetProductDto> preprareGetProductDtoList() {
		GetProductDto productDto1 = new GetProductDto(1L, "gloves", "10oz Boxing Gloves", "Gloves.jpg", 12, 40.0, "10oz Boxing Gloves");
		GetProductDto productDto2 = new GetProductDto(2L, "gloves", "12oz Boxing Gloves", "Gloves.jpg", 26, 50.0, "12oz Boxing Gloves");
		GetProductDto productDto3 = new GetProductDto(3L, "gloves", "14oz Boxing Gloves", "Gloves.jpg", 18, 60.0, "14oz Boxing Gloves");
		GetProductDto productDto4 = new GetProductDto(4L, "headguard", "Male HeadGuard", "Headguard.jpg", 12, 70.0, "Male HeadGuard");
		GetProductDto productDto5 = new GetProductDto(5L, "headguard", "Female HeadGuard", "Headguard.jpg", 6, 70.0, "Female HeadGuard");
		GetProductDto productDto6 = new GetProductDto(6L, "headguard", "Child HeadGuard", "Headguard.jpg", 3, 50.0, "Child HeadGuard");
		GetProductDto productDto7 = new GetProductDto(7L, "bag", "Punching Bag", "HookBag.jpg", 10, 200.0, "Punching bag for practicing hooks");
		GetProductDto productDto8 = new GetProductDto(8L, "bag", "Common Punching Bag", "HookBag.jpg", 22, 250.0, "Punching bag for general practicing");
		GetProductDto productDto9 = new GetProductDto(9L, "vandages", "5m Vandages", "Vandages.jpg", 32, 25.0, "5m Vandages");
		GetProductDto productDto10 = new GetProductDto(10L, "vandages", "4m Vandages", "Vandages.jpg", 40, 25.0, "4m Vandages");
		GetProductDto productDto11 = new GetProductDto(11L, "vandages", "3m Vandages", "Vandages.jpg", 44, 20.0, "3m Vandages");
		GetProductDto productDto12 = new GetProductDto(12L, "pads", "Regular Hand Pads", "Pads.png", 24, 50.0, "Pads for practicing technic");
		GetProductDto productDto13 = new GetProductDto(13L, "pads", "Small Hand Pads", "Pads.png", 14, 20.0, "Pads for practicing technic");
		GetProductDto productDto14 = new GetProductDto(14L, "mouthpiece", "Simple Mouthpiece", "Mouthpiece.jpg", 67, 20.0, "Simple to adapt mouthpiece");
		GetProductDto productDto15 = new GetProductDto(15L, "mouthpiece", "Pro Mouthpiece", "Mouthpiece.jpg", 26, 50.0, "Professional mouthpiece");

		List<GetProductDto> productDtoList = new ArrayList<>();
		productDtoList.add(productDto1);
		productDtoList.add(productDto2);
		productDtoList.add(productDto3);
		productDtoList.add(productDto4);
		productDtoList.add(productDto5);
		productDtoList.add(productDto6);
		productDtoList.add(productDto7);
		productDtoList.add(productDto8);
		productDtoList.add(productDto9);
		productDtoList.add(productDto10);
		productDtoList.add(productDto11);
		productDtoList.add(productDto12);
		productDtoList.add(productDto13);
		productDtoList.add(productDto14);
		productDtoList.add(productDto15);

		return productDtoList;
	}

}
