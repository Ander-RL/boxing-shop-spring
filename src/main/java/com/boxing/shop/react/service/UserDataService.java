package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.GetOrderDto;
import com.boxing.shop.react.dto.GetUserDataDto;
import com.boxing.shop.react.entity.ApplicationUser;
import com.boxing.shop.react.entity.Order;
import com.boxing.shop.react.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDataService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private OrderService orderService;

    public GetUserDataDto loadUserDataByUsername(String username) throws UsernameNotFoundException {

        // TODO: loadUserByUsername
        //  -> load user personal data
        //  -> load user orders data
        //  -> load user address book data
        //  -> load user wishlist data

        System.out.println("[UserService][loadApplicationUserData] username: " + username);
        System.out.println("[UserService][loadApplicationUserData] database found: " + userRepository.findByUsername(username));

        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " user is not valid"));

        List<GetOrderDto> orderList = orderService
                .getOrdersByIdList(user.getOrders().stream().map(Order::getOrderId).collect(Collectors.toList()));

        GetUserDataDto userData = new GetUserDataDto();
        userData.setUsername(username);
        userData.setOrders(orderList);

        return userData;
    }
}
