package com.store.controllers;

import com.store.dto.UserRegistrationDto;
import com.store.entity.User;
import com.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@Api(tags = " User controller", description = " Operations with user ")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "Create user")
    public ResponseEntity<String> getCreatedUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.registerUser(userRegistrationDto);
        return new ResponseEntity<>("User was created", HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.findAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
