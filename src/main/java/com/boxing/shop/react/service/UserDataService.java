package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.GetAddressDto;
import com.boxing.shop.react.dto.GetOrderDto;
import com.boxing.shop.react.dto.GetUserDataDto;
import com.boxing.shop.react.entity.ApplicationUser;
import com.boxing.shop.react.entity.Order;
import com.boxing.shop.react.entity.UserAddress;
import com.boxing.shop.react.mapper.IUserAddressMapper;
import com.boxing.shop.react.repository.IUserAddressRepository;
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
    private IUserAddressRepository userAddressRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private IUserAddressMapper userAddressMapper;

    public GetUserDataDto loadUserDataByUsername(String username) throws UsernameNotFoundException {

        // TODO: loadUserByUsername
        //  -> load user personal data
        //  -> load user wishlist data

        System.out.println("[UserService][loadApplicationUserData] username: " + username);
        System.out.println("[UserService][loadApplicationUserData] database found: " + userRepository.findByUsername(username));

        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " user is not valid"));

        List<GetOrderDto> orderList = orderService
                .getOrdersByIdList(user.getOrders().stream().map(Order::getOrderId).collect(Collectors.toList()));

        List<UserAddress> userAddressList = userAddressRepository.findAllById(user.getUserId());
        List<GetAddressDto> userAddressDtoList = userAddressList.stream().map((address) -> userAddressMapper.entityToDto(address)).toList();

        GetUserDataDto userData = new GetUserDataDto();
        userData.setUsername(username);
        userData.setFirstName(user.getFirstName());
        userData.setSecondName(user.getSecondName());
        userData.setBirthDate(user.getBirthDate());
        userData.setOrders(orderList);
        userData.setEmail(user.getEmail());
        userData.setAddressList(userAddressDtoList);
        System.out.println("[UserService][loadApplicationUserData] returning userData: " + userData);

        return userData;
    }
}
