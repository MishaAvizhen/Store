package com.store.controllers;

import com.store.cart.CartsManager;
import com.store.service.CartService;
import com.store.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@SessionAttributes("cart")
@RequestMapping(value = "/api/cart")
@Api(tags = " Cart controller", description = " Operations with cart ")
public class CartRestController {

    private CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/addToCart/{itemId}")
    @ApiOperation(value = "Add item to cart")
    public ResponseEntity<String> addItemToCart(@PathVariable Long itemId, Principal principal) {
        String username = principal.getName();
        cartService.addToCart(itemId, username);
        return new ResponseEntity<String>("Item was added to cart ", HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Get items in cart")
    public ResponseEntity <List<Long>> cart(Principal principal) {
        List<Long> cartForCurrentUser = cartService.getCartForCurrentUser(principal.getName());
        return new ResponseEntity<>(cartForCurrentUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete item from cart")
    public ResponseEntity<String> removeFromCart(@PathVariable Long id, Principal principal) {
        cartService.deleteFromCart(id, principal.getName());
        return new ResponseEntity<>("Item was deleted from cart ", HttpStatus.OK);
    }

    @PostMapping(value = "/makeOrder")
    @ApiOperation(value = "Make order")
    public ResponseEntity<String> makeOrder(Principal principal) {
        cartService.makeOrderByCart(principal.getName());
        return new ResponseEntity<>("Order is completed", HttpStatus.OK);
    }
}
