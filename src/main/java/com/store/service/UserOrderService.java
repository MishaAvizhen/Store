package com.store.service;

import com.store.entity.User;
import com.store.entity.UserOrder;

import java.util.List;

public interface UserOrderService {
    UserOrder makeOrder(User orderedByUser, List<Long> itemIds);

}
