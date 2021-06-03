package com.store.service;

import com.store.dto.FilteredItemsDto;
import com.store.dto.ItemDto;
import com.store.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    void delete(Long id);

    List<Item> filteredItem(FilteredItemsDto filteredItemsDto);

    Item updateItem(ItemDto itemDto, Long itemId, String username);

    Item forceUpdateItem(ItemDto itemDto, Long itemId, String username);

    Item addItemToCatalog(ItemDto itemDto);

    Item findById(Long itemId);
}
