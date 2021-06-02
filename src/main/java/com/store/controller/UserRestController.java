package com.store.controller;

import com.store.dto.UserRegistrationDto;
import com.store.entity.User;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> getCreatedUser(@RequestBody UserRegistrationDto userRegistrationDto) {
         userService.registerUser(userRegistrationDto);
        return new ResponseEntity<>("User was created", HttpStatus.OK);
    }

}
