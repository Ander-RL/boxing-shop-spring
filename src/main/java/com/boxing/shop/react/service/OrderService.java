package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.*;
import com.boxing.shop.react.entity.Order;
import com.boxing.shop.react.entity.OrderProduct;
import com.boxing.shop.react.mapper.IOrderMapper;
import com.boxing.shop.react.mapper.IProductMapper;
import com.boxing.shop.react.repository.IOrderProductRepository;
import com.boxing.shop.react.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final IOrderRepository orderRepository;

    private final IOrderProductRepository orderProductRepository;

    private final IOrderMapper orderMapper;

    private final IProductMapper productMapper;


    /**
     * Return list of orders
     * @return List<GetOrderDto>
     */
    @Transactional(readOnly = true)
    public List<GetOrderDto> getOrders(){

        List<Order> orders = orderRepository.findAll();
        List<GetOrderDto> orderDtoList = new ArrayList<>();

        for(Order order : orders) {
            List<OrderProduct> postOrderProductDtoList = order.getProducts();
            List<GetOrderProductDto> getOrderProductDtoList = new ArrayList<>();

            GetOrderDto orderDto = mapOrderProductsToDtos(postOrderProductDtoList, getOrderProductDtoList, order);

            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }

    /**
     * Return the order by id
     * @param orderId orderId
     * @return Optional<GetOrderDto>
     */
    @Transactional(readOnly = true)
    public Optional<GetOrderDto> getOrder(Long orderId){

        Order order = orderRepository.findById(orderId).orElseThrow();
        List<OrderProduct> orderProducts =  order.getProducts();
        List<GetOrderProductDto> orderProductDtos = new ArrayList<>();

        GetOrderDto orderDto = mapOrderProductsToDtos(orderProducts, orderProductDtos, order);

        return Optional.of(orderDto);
    }

    /**
     * Return the order by id
     * @param postOrderDto PostOrderDto
     * @return String
     */
    @Transactional()
    public Order postOrder(PostOrderDto postOrderDto){

        List<OrderProduct> products = postOrderDto.getProducts().stream().map(productMapper::dtoToEntity).toList();
        orderProductRepository.saveAll(products);

        Order order = new Order();
        order.setCustomerId(postOrderDto.getCustomerId());
        order.setProducts(products);
        order.setTotalAmount(calculateTotalAmount(products));

        return orderRepository.save(order);
    }

    private Double calculateTotalAmount(List<OrderProduct> products) {
        double total = 0.0;
        for(OrderProduct product : products){
            total += product.getUnitaryAmount() * product.getQuantity();
        }
        return total;
    }

    private GetOrderDto mapOrderProductsToDtos(List<OrderProduct> orderProducts, List<GetOrderProductDto> orderProductDtos, Order order) {
        for(OrderProduct orderProduct : orderProducts) {
            orderProductDtos.add(productMapper.entityToDto(orderProduct));
        }
        GetOrderDto orderDto = orderMapper.entityToDto(order);
        orderDto.setProducts(orderProductDtos);
        return orderDto;
    }
}
