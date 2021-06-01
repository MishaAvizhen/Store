package com.store.controller;

import com.store.cart.CartsManager;
import com.store.entity.User;
import com.store.service.CartService;
import com.store.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@SessionAttributes("cart")
@RequestMapping(value = "/api/cart")
public class CartRestController {
    private ItemService itemService;
    private CartService cartService;
    private CartsManager cartsManager;

    public CartRestController(ItemService itemService, CartService cartService, CartsManager cartsManager) {
        this.itemService = itemService;
        this.cartService = cartService;
        this.cartsManager = cartsManager;
    }

    @GetMapping(value = "/addToCart/{itemId}")
    public ResponseEntity<String> addItemToCart(@PathVariable Long itemId, Principal principal) {
        String username = principal.getName();
        cartService.addToCart(itemId, username);
        return new ResponseEntity<String>("Item was added to cart ", HttpStatus.OK);
    }

    @GetMapping(value = "/cart")
    public ResponseEntity <List<Long>> cart(Principal principal) {
        List<Long> cartForCurrentUser = cartService.getCartForCurrentUser(principal.getName());
        return new ResponseEntity<>(cartForCurrentUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long id, Principal principal) {
        cartService.deleteFromCart(id, principal.getName());
        return new ResponseEntity<>("Item was deleted from cart ", HttpStatus.OK);
    }

    @PostMapping(value = "/makeOrder")
    public ResponseEntity<String> makeOrder(Principal principal) {
        cartService.makeOrderByCart(principal.getName());
        return new ResponseEntity<>("Order is completed", HttpStatus.OK);
    }

}
