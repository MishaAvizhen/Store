package com.store.cart;

import com.store.entity.User;
import com.store.exception.ResourceNotFoundException;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class CartsManager {
    private final Map<User, List<Long>> userToCartMap = new HashMap<>();

    public Map<User, List<Long>> getUserToCartMap() {
        return userToCartMap;
    }

    private UserService userService;

    @Autowired
    public CartsManager(UserService userService) {
        this.userService = userService;
    }

    private List<Long> getCartForUser(User user) {
        if (!userToCartMap.containsKey(user)) {
            userToCartMap.put(user, new LinkedList<>());
        }
        return userToCartMap.get(user);
    }

    public List<Long> getCartForUsername(String username) {
        User userByUsername = userService.findUserByUsername(username);
        if (userByUsername != null) {
            return getCartForUser(userByUsername);
        }
        throw new ResourceNotFoundException("User not found with username: " + username);
    }

}
