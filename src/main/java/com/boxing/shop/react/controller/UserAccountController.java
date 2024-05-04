package com.boxing.shop.react.controller;

import com.boxing.shop.react.dto.GetUserDataDto;
import com.boxing.shop.react.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/account")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserAccountController {

    private final UserDataService userDataService;

    /**
     * Return user account data
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public GetUserDataDto getUser(@RequestParam(name = "username") String username){

        return userDataService.loadUserDataByUsername(username);
    }
}
