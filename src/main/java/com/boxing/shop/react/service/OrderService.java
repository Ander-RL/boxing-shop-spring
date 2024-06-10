package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.*;
import com.boxing.shop.react.entity.ApplicationUser;
import com.boxing.shop.react.entity.Order;
import com.boxing.shop.react.entity.OrderProduct;
import com.boxing.shop.react.entity.Product;
import com.boxing.shop.react.mapper.IOrderMapper;
import com.boxing.shop.react.mapper.IProductMapper;
import com.boxing.shop.react.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final IOrderRepository orderRepository;

    private final IUserRepository userRepository;

    private final IProductRepository productRepository;

    private final IOrderProductRepository orderProductRepository;

    private final IOrderMapper orderMapper;

    private final IProductMapper productMapper;

    private final UserService userService;


    /**
     * Return list of orders for authenticated user
     * @return List<GetOrderDto>
     */
    @Transactional(readOnly = true)
    public List<GetOrderDto> getOrders(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("[OrderService][getOrders] username: " + username);
        Optional<ApplicationUser> userData = userRepository.findByUsername(username);
        System.out.println("[OrderService][getOrders] userData.isPresent(): " + userData.isPresent());
        long userId = userData.isPresent() ? userData.get().getUserId() : 0L;
        System.out.println("[OrderService][getOrders] userId: " + userId);

        List<GetOrderDto> orderDtoList = new ArrayList<>();

        if(userId == 0L) return orderDtoList;

        List<Order> orders = orderRepository.findByUserId(String.valueOf(userId));
        System.out.println("[OrderService][getOrders] order list: " + orders);

        for(Order order : orders) {
            List<OrderProduct> postOrderProductDtoList = order.getProducts();
            List<GetOrderProductDto> getOrderProductDtoList = new ArrayList<>();

            GetOrderDto orderDto = mapOrderProductsToDtos(postOrderProductDtoList, getOrderProductDtoList, order);

            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }
    /**
     * Return list of orders based on a list of orderId
     * @return List<GetOrderDto>
     */
    @Transactional(readOnly = true)
    public List<GetOrderDto> getOrdersByIdList(List<Long> id){

        List<Order> orders = orderRepository.findAllById(id);
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
     * @return Order
     */
    @Transactional()
    public GetOrderDto postOrder(PostOrderDto postOrderDto, String username){

        List<OrderProduct> products = postOrderDto.getProducts().stream().map(productMapper::dtoToEntity).toList();
        orderProductRepository.saveAll(products);

        ApplicationUser applicationUser = userService.loadApplicationUserByUsername(username);

        Order order = new Order();
        order.setCustomerId(applicationUser.getUserId());
        order.setProducts(products);
        order.setTotalAmount(calculateTotalAmount(products));

        /*try {
            Thread.sleep(5000);
            //Any other code to execute after 5 sec execution pause.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        applicationUser.getOrders().add(order);

        userService.updateUser(applicationUser);

        return orderMapper.entityToDto(orderRepository.save(order));
    }

    /**
     * Return total amount of the order
     * @param products List<OrderProduct>
     * @return Double
     */
    private Double calculateTotalAmount(List<OrderProduct> products) {
        double total = 0.0;
        for(OrderProduct orderProduct : products){
            Product product = productRepository.findById(orderProduct.getPurchasedProduct()).orElseThrow();
            total += product.getUnitaryAmount() * orderProduct.getQuantity();
        }
        return total;
    }

    /**
     * Maps entities to DTOs
     * @param orderProducts List<OrderProduct>, orderProductDtos List<GetOrderProductDto>, order Order
     * @return GetOrderDto
     */
    private GetOrderDto mapOrderProductsToDtos(List<OrderProduct> orderProducts, List<GetOrderProductDto> orderProductDtos, Order order) {
        for(OrderProduct orderProduct : orderProducts) {
            orderProductDtos.add(productMapper.entityToDto(orderProduct));
        }
        GetOrderDto orderDto = orderMapper.entityToDto(order);
        orderDto.setProducts(orderProductDtos);
        return orderDto;
    }
}
