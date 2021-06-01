package com.store.service.impl;

import com.store.cart.CartsManager;
import com.store.converter.impl.ItemConverter;
import com.store.dto.FilteredItemsDto;
import com.store.dto.ItemDto;
import com.store.entity.Item;
import com.store.entity.User;
import com.store.exception.ResourceAlreadyExist;
import com.store.repository.ItemRepository;
import com.store.service.ItemService;
import com.store.service.MailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemConverter itemConverter;
    private ItemRepository itemRepository;
    private CartsManager cartsManager;
    private MailService mailService;

    public ItemServiceImpl(ItemConverter itemConverter, ItemRepository itemRepository, CartsManager cartsManager, MailService mailService) {
        this.itemConverter = itemConverter;
        this.itemRepository = itemRepository;
        this.cartsManager = cartsManager;
        this.mailService = mailService;
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);

    }

    @Override
    public List<Item> filteredItem(FilteredItemsDto filteredItemsDto) {
        List<Item> itemList = itemRepository.findAll();
        return itemList.stream()
                .filter(item -> filteredItemsDto.getTitle() == null || filteredItemsDto.getTitle().equals(item.getTitle()))
                .filter(item -> filteredItemsDto.getDescription() == null || filteredItemsDto.getDescription().equals(item.getDescription()))
                .filter(item -> filteredItemsDto.getTags() == null || filteredItemsDto.getTags().equals(item.getTags()))
                .collect(Collectors.toList());
    }

    @Override
    public Item updateItem(ItemDto itemDto, Long itemId, String username) {
        Map<User, List<Long>> userToCartMap = cartsManager.getUserToCartMap();
        for (List<Long> longList : userToCartMap.values()) {
            if (longList.contains(itemId)) {
                throw new ResourceAlreadyExist("This item currently in cart");
            }
        }
        Item itemToUpdate = itemRepository.getOne(itemId);
        Item itemAfterUpdate = itemConverter.convertEntityToUpdate(itemDto, itemToUpdate);
        return itemRepository.saveAndFlush(itemAfterUpdate);
    }
    @Override
    public Item addItemToCatalog(ItemDto itemDto) {
        Item item = itemConverter.convertToEntity(itemDto);
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        return itemRepository.findById(itemId);
    }


    @Override
    public Item forceUpdateItem(ItemDto itemDto, Long itemId, String username) {
        Map<User, List<Long>> userToCartMap = cartsManager.getUserToCartMap();
        Item itemToUpdate = itemRepository.getOne(itemId);
        Item oldItem = itemRepository.getOne(itemId);
        Item itemAfterUpdate = itemConverter.convertEntityToUpdate(itemDto, itemToUpdate);
        for (Map.Entry<User, List<Long>> userListEntry : userToCartMap.entrySet()) {
            User user = userListEntry.getKey();
            List<Long> cartIdList = userListEntry.getValue();
            if (cartIdList.contains(itemId)) {
                mailService.sendMail(user.getEmail(), buildEmailMessage(oldItem, itemAfterUpdate), "Item was updated");
            }
        }

        return itemRepository.saveAndFlush(itemAfterUpdate);
    }

    private String buildEmailMessage(Item oldItem, Item itemAfterUpdate) {
        StringBuilder str = new StringBuilder();
        str
                .append("Добрый день!\n")
                .append("Товар в корзине  изменен: ").append("\n")
                .append("Было: \n")
                .append("Название: ")
                .append(oldItem.getTitle()).append(", ")
                .append("Описание: ")
                .append(oldItem.getDescription()).append("\n")
                .append("Стало: \n")
                .append("Название: ")
                .append(itemAfterUpdate.getTitle()).append(", ")
                .append("Описание: ")
                .append(itemAfterUpdate.getDescription()).append("\n");
        str
                .append("Спасибо!!!");


        return str.toString();
    }

}
