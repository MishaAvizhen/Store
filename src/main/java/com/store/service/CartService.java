package com.store.service;

import com.store.entity.Item;

import java.util.List;

public interface CartService {
    void addToCart(Long itemId, List<Long> cart);

    void deleteFromCart(Long id);
}
