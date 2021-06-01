package com.store.service;

import java.util.List;

public interface CartService {
    void addToCart(Long itemId, String username);

    void deleteFromCart(Long id, String username);

    List<Long> getCartForCurrentUser(String username);

    void makeOrderByCart(String username);
}
