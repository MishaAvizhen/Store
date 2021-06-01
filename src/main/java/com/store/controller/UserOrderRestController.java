package com.store.controller;

import com.store.entity.User;
import com.store.entity.UserOrder;
import com.store.service.UserOrderService;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.List;

@RestController
@SessionAttributes("cart")
@RequestMapping(value = "/api/userOrder")
public class UserOrderRestController {
    private UserOrderService userOrderService;
    private UserService userService;

    @Autowired
    public UserOrderRestController(UserOrderService userOrderService, UserService userService) {
        this.userOrderService = userOrderService;
        this.userService = userService;
    }

    @PostMapping(value = "/makeOrder")
    public ResponseEntity<UserOrder> makeOrder(Principal principal, ModelMap modelMap) {
        List<Long> cart = (List<Long>) modelMap.get("cart");
        User currentUser = userService.findUserByUsername(principal.getName());
        UserOrder userOrder = userOrderService.makeOrder(currentUser, cart);
        return new ResponseEntity<UserOrder>(userOrder, HttpStatus.OK);
    }
}
