package com.boxing.shop.react.controller;

import com.boxing.shop.react.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/v1/userdetails")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    /**
     * Return user account data
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetails getUser(@RequestParam(name = "username") String name){

        return userService.loadUserByUsername(name);
    }
}
