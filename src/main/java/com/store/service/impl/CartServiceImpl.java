package com.store.service.impl;

import com.store.cart.CartsManager;
import com.store.entity.Item;
import com.store.entity.User;
import com.store.exception.ResourceAlreadyExist;
import com.store.exception.ResourceNotFoundException;
import com.store.service.CartService;
import com.store.service.ItemService;
import com.store.service.UserOrderService;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private ItemService itemService;
    private CartsManager cartsManager;
    private UserService userService;
    private UserOrderService userOrderService;

    @Autowired
    public CartServiceImpl(ItemService itemService, CartsManager cartsManager, UserService userService, UserOrderService userOrderService) {
        this.itemService = itemService;
        this.cartsManager = cartsManager;
        this.userService = userService;
        this.userOrderService = userOrderService;
    }

    @Override
    public void addToCart(Long itemId, String username) {
        Optional<Item> item = itemService.findById(itemId);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException(itemId);
        }
        List<Long> cart = cartsManager.getCartForUsername(username);
        if (cart.contains(itemId)) {
            throw new ResourceAlreadyExist(itemId);
        }
        cart.add(itemId);
    }

    @Override
    public void deleteFromCart(Long id, String username) {
        List<Long> cart = cartsManager.getCartForUsername(username);
        if (!cart.contains(id)) {
            throw new ResourceNotFoundException(id);
        }
        cart.remove(id);
    }

    @Override
    public List<Long> getCartForCurrentUser(String username) {
        return cartsManager.getCartForUsername(username);
    }

    @Override
    public void makeOrderByCart(String username) {
        List<Long> cartForUsername = cartsManager.getCartForUsername(username);
        User userByUsername = userService.findUserByUsername(username);
        userOrderService.makeOrder(userByUsername, cartForUsername);
        cartForUsername.clear();

    }
}
