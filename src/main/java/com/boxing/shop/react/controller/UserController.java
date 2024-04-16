package com.boxing.shop.react.controller;

import com.boxing.shop.react.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    /**
     * Return user
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUser(){

        return "User controller works";
    }
}
