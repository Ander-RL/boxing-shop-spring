package com.boxing.shop.react.controller;

import com.boxing.shop.react.dto.GetOrderDto;
import com.boxing.shop.react.dto.PostOrderDto;
import com.boxing.shop.react.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/orders")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    /**
     * Return list of orders
     * @return List<GetOrderDto>
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetOrderDto> getOrders(){

        return orderService.getOrders();
    }

    /**
     * Return the order by id
     * @param orderId Long
     * @return GetProductDto
     */
    @GetMapping(path = "/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetOrderDto getOrder(@PathVariable Long orderId){

        return orderService.getOrder(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Create a new order
     * @param postOrderDto PostOrderDto
     * @return String
     */
    @PostMapping(path = "/checkout",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String postOrder(@RequestBody PostOrderDto postOrderDto){

        return orderService.postOrder(postOrderDto);
    }
}
