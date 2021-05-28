package com.store.service;

import com.store.dto.FilteredItemsDto;
import com.store.dto.ItemDto;
import com.store.entity.Item;

import java.util.List;

public interface ItemService {

    void delete(Long id);

    List<Item> filteredItem(FilteredItemsDto filteredItemsDto);

    Item updateItem(ItemDto itemDto, Long itemId);
}
