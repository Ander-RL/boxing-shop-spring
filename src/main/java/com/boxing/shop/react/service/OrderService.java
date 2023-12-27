package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.GetOrderDto;
import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.entity.Product;
import com.boxing.shop.react.mapper.IOrderMapper;
import com.boxing.shop.react.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final IOrderRepository orderRepository;

    private final IOrderMapper orderMapper;

    /**
     * Return list of orders
     * @return List<GetOrderDto>
     */
    @Transactional(readOnly = true)
    public List<GetOrderDto> getOrders(){

        return orderRepository.findAll()
                .stream()
                .map(orderMapper::entityToDto)
                .collect(Collectors.toList());
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
}
