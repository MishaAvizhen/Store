package com.store.service.impl;

import com.store.entity.Item;
import com.store.repository.ItemRepository;
import com.store.service.CartService;
import com.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Optional;

@SessionAttributes("cart")
@Service
public class CartServiceImpl implements CartService {

    private ItemRepository itemRepository;
    private ItemService itemService;

    @Autowired
    public CartServiceImpl(ItemRepository itemRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @Override
    public void addToCart(Long itemId, List<Long> cart) {
        Optional<Item> item = itemService.findById(itemId);
        if (!item.isPresent()) {
            throw new IllegalArgumentException("This item not found");
        }
        if (cart.contains(itemId)) {
            throw new IllegalArgumentException("This item already exist in cart");
        }
        cart.add(itemId);
    }

    @Override
    public void deleteFromCart(Long id) {


    }
}
