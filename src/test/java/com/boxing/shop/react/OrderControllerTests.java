package com.boxing.shop.react;

import com.boxing.shop.react.constants.Constants;
import com.boxing.shop.react.controller.OrderController;
import com.boxing.shop.react.dto.GetOrderDto;
import com.boxing.shop.react.dto.PostOrderDto;
import com.boxing.shop.react.entity.Order;
import com.boxing.shop.react.mapper.IOrderMapper;
import com.boxing.shop.react.repository.IOrderRepository;
import com.boxing.shop.react.service.OrderService;
import com.boxing.shop.react.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OrderControllerTests {

	@Mock
	private IOrderRepository orderRepository;
	@Mock
	private OrderService orderService;

	@Autowired
	private OrderController orderController;

	@Autowired
	private IOrderMapper orderMapper;

	@Test
	void contextLoads() {
	}

	@Test
	@WithMockUser(roles = "ADMIN", value = "ander", username = "ander", password = "123")
	void getOrdersControllerTest() {
		Mockito.when(orderRepository.findByUserId("ander")).thenReturn(preprareOrderList());
		List<GetOrderDto> getOrderDtoList = orderController.getOrders();
		List<Object> expectedOrderList = TestUtil.leerJSONasDTOList(Constants.GetOrderDtoListPath, GetOrderDto.class);

		Assertions.assertEquals(expectedOrderList, getOrderDtoList);
	}

	@Test
	void getOrderByIdControllerTest() {
		Mockito.when(orderRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable((Order) TestUtil.leerJSONasDTO(Constants.OrderPath, Order.class)));
		GetOrderDto order = orderController.getOrder(1000L);
		GetOrderDto expectedOrder = (GetOrderDto) TestUtil.leerJSONasDTO(Constants.GetOrderDtoPath, GetOrderDto.class);

		Assertions.assertEquals(order, expectedOrder);
	}

	@Test
	void postOrderControllerTest() {
		Mockito.when(orderRepository.save(Mockito.any())).thenReturn(Optional.ofNullable((Order) TestUtil.leerJSONasDTO(Constants.OrderPath, Order.class)));
		GetOrderDto order = orderController.postOrder((PostOrderDto) TestUtil.leerJSONasDTO(Constants.PostOrderDtoPath, PostOrderDto.class), "ander");
		GetOrderDto expectedOrder = (GetOrderDto) TestUtil.leerJSONasDTO(Constants.GetOrderDtoPath, GetOrderDto.class);

		Assertions.assertEquals(order, expectedOrder);
	}

	private List<Order> preprareOrderList() {
		List<Order> entityList = new ArrayList<>();
		List<Object> orderList = TestUtil.leerJSONasDTOList(Constants.OrderListPath, Order.class);

		for(Object order : orderList){
			entityList.add((Order) order);
		}

		return entityList;
	}

}
