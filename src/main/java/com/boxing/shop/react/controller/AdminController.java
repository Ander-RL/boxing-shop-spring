package com.boxing.shop.react.controller;

import com.boxing.shop.react.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/admin")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {

    private final UserService userService;

    /**
     * Return admin
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAdmin(){

        return "Admin controller works";
    }
}
