package com.store.service.impl;

import com.store.entity.Item;
import com.store.entity.OrderItem;
import com.store.entity.User;
import com.store.entity.UserOrder;
import com.store.repository.ItemRepository;
import com.store.repository.OrderItemRepository;
import com.store.repository.UserOrderRepository;
import com.store.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    private UserOrderRepository userOrderRepository;
    private ItemRepository itemRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public UserOrderServiceImpl(UserOrderRepository userOrderRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.userOrderRepository = userOrderRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public UserOrder makeOrder(User orderedByUser, List<Long> itemIds) {
        List<Item> items = new ArrayList<>();
        UserOrder userOrder = new UserOrder();
        userOrder.setOrderedByUser(orderedByUser);
        UserOrder savedUserOrder = userOrderRepository.save(userOrder);
        for (Long itemId : itemIds) {
            Optional<Item> itemOptional = itemRepository.findById(itemId);
            if (itemOptional.isPresent()) {
                Item item = itemOptional.get();
                items.add(item);
            }
        }

        List<OrderItem> savedUserOrderItems = new ArrayList<>();

        for (Item item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setUserOrder(savedUserOrder);
            OrderItem saveOrderItem = orderItemRepository.save(orderItem);
            savedUserOrderItems.add(saveOrderItem);
        }
        savedUserOrder.setOrderItems(savedUserOrderItems);
        itemIds.clear();
        return userOrderRepository.save(savedUserOrder);
    }
}
