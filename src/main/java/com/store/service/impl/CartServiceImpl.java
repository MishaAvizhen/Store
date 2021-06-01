package com.store.service.impl;

import com.store.entity.Item;
import com.store.exception.ResourceAlreadyExist;
import com.store.exception.ResourceNotFoundException;
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

    private ItemService itemService;

    @Autowired
    public CartServiceImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void addToCart(Long itemId, List<Long> cart) {
        Optional<Item> item = itemService.findById(itemId);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException(itemId.toString());
        }
        if (cart.contains(itemId)) {
            throw new ResourceAlreadyExist(itemId.toString());
        }
        cart.add(itemId);
    }

    @Override
    public void deleteFromCart(Long id, List<Long> cart ) {
        if (!cart.contains(id)) {
            throw new ResourceNotFoundException(id.toString());
        }
        cart.remove(id);
    }
}
