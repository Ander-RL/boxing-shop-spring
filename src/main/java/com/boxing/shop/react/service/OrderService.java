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
            for(OrderProduct orderProduct : postOrderProductDtoList) {
                getOrderProductDtoList.add(productMapper.entityToDto(orderProduct));
            }
            GetOrderDto orderDto = orderMapper.entityToDto(order);
            orderDto.setProducts(getOrderProductDtoList);

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

        return orderRepository.findById(orderId)
                .map(orderMapper::entityToDto);
    }

    /**
     * Return the order by id
     * @param postOrderDto PostOrderDto
     * @return String
     */
    @Transactional()
    public String postOrder(PostOrderDto postOrderDto){

        List<OrderProduct> products = postOrderDto.getProducts().stream().map(productMapper::dtoToEntity).toList();
        orderProductRepository.saveAll(products);

        Order order = new Order();
        order.setCustomerId(postOrderDto.getCustomerId());
        order.setProducts(products);
        order.setTotalAmount(calculateTotalAmount(products));

        return orderRepository.save(order).toString();
    }

    private Double calculateTotalAmount(List<OrderProduct> products) {
        double total = 0.0;
        for(OrderProduct product : products){
            total += product.getUnitaryAmount() * product.getQuantity();
        }
        return total;
    }
}
