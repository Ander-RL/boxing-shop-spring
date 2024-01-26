package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.GetOrderDto;
import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.dto.PostOrderDto;
import com.boxing.shop.react.entity.Order;
import com.boxing.shop.react.entity.Product;
import com.boxing.shop.react.mapper.IOrderMapper;
import com.boxing.shop.react.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final IOrderRepository orderRepository;

    private final IOrderMapper orderMapper;

    private final AtomicLong idOrderGenerator = new AtomicLong(0);

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

    /**
     * Return the order by id
     * @param postOrderDto List<String>
     * @return String
     */
    @Transactional()
    public String postOrder(List<PostOrderDto> postOrderDto){

        Long idOrder = generateOrderId(); // Generate idOrder

        List<Order> orders = postOrderDto.stream()
                .map(dto -> orderMapper.dtoToEntity(dto, idOrder))
                .collect(Collectors.toList());

        return orderRepository.saveAll(orders).toString();
    }

    private Long generateOrderId() {
        return idOrderGenerator.incrementAndGet();
    }
}
