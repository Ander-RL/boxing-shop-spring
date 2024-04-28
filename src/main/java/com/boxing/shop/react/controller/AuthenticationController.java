package com.boxing.shop.react.controller;

import com.boxing.shop.react.dto.LoginResponseDto;
import com.boxing.shop.react.dto.PostApplicationUserDto;
import com.boxing.shop.react.dto.PostRegistrationUserDto;
import com.boxing.shop.react.entity.ApplicationUser;
import com.boxing.shop.react.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * saveUser
     * @return String
     */
    @PostMapping(path="/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApplicationUser saveUser(@RequestBody PostRegistrationUserDto postRegistrationUserDto){

        return authenticationService.registerUser(postRegistrationUserDto);
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody PostApplicationUserDto credentials){
        return authenticationService.loginUser(credentials);
    }
}
