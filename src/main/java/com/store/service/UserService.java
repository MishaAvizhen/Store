package com.store.service;

import com.store.dto.UserRegistrationDto;
import com.store.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User registerUser(UserRegistrationDto userRegistrationDto);

    User findUserByUsername(String username);
}
