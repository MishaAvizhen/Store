package com.store.service.impl;

import com.store.converter.impl.ItemConverter;
import com.store.dto.FilteredItemsDto;
import com.store.dto.ItemDto;
import com.store.entity.Item;
import com.store.repository.ItemRepository;
import com.store.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemConverter itemConverter;
    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemConverter itemConverter, ItemRepository itemRepository) {
        this.itemConverter = itemConverter;
        this.itemRepository = itemRepository;
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
    public Item updateItem(ItemDto itemDto, Long itemId) {
        Item itemToUpdate = itemRepository.getOne(itemId);
        Item itemAfterUpdate = itemConverter.convertEntityToUpdate(itemDto, itemToUpdate);
        return itemRepository.saveAndFlush(itemAfterUpdate);
    }

    @Override
    public Item addItemToCatalog(ItemDto itemDto) {
        Item item = itemConverter.convertToEntity(itemDto);
        return itemRepository.save(item);
    }

}
