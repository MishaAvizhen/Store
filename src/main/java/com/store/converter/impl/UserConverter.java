package com.store.converter.impl;

import com.store.constants.Role;
import com.store.converter.Converter;
import com.store.dto.UserRegistrationDto;
import com.store.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserRegistrationDto> {
    @Override
    public User convertToEntity(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        user.setRole(Role.USER);
        return user;
    }

    @Override
    public User convertEntityToUpdate(UserRegistrationDto dto, User entity) {
        return null;
    }
}
