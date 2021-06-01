package com.store.controller;

import com.store.entity.Item;
import com.store.service.CartService;
import com.store.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@SessionAttributes("cart")
@RequestMapping(value = "/api/cart")
public class CartRestController {
    private ItemService itemService;
    private CartService cartService;

    public CartRestController(ItemService itemService, CartService cartService) {
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @GetMapping(value = "/addToCart/{itemId}")
    public ResponseEntity<String> addItemToCart(@PathVariable Long itemId, ModelMap modelMap) {
        initSession(modelMap);
        List<Long> cart = (List<Long>) modelMap.get("cart");
        cartService.addToCart(itemId, cart);
        return new ResponseEntity<String>("Item was added to cart ", HttpStatus.OK);
    }

    @GetMapping(value = "/cart")
    public ResponseEntity<List<Long>> cart(ModelMap modelMap) {
        initSession(modelMap);
        List<Long> cart = (List<Long>) modelMap.get("cart");
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long itemId, ModelMap modelMap) {
        List<Long> cart = (List<Long>) modelMap.get("cart");
        cart.remove(itemId);
        return new ResponseEntity<>("Item was  deleted with id: " + itemId, HttpStatus.OK);
    }

    private void initSession(ModelMap model) {
        if (!model.containsAttribute("cart")) {
            model.addAttribute("cart", new LinkedList<Long>());
        }
    }

}
