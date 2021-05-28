package com.store.controller;

import com.store.converter.impl.ItemConverter;
import com.store.dto.FilteredItemsDto;
import com.store.dto.ItemDto;
import com.store.entity.Item;
import com.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/items")
public class ItemRestController {
    private ItemService itemService;
    private ItemConverter itemConverter;

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems(@RequestParam(value = "description", required = false) String description,
                                  @RequestParam(value = "tags", required = false) String tags,
                                  @RequestParam(value = "title", required = false) String title) {
        FilteredItemsDto filteredItemsDto = new FilteredItemsDto(description, tags, title);
        return itemService.filteredItem(filteredItemsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteItemsById(@PathVariable Long id) {
        itemService.delete(id);

    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id,
                           @RequestBody ItemDto itemDto) {

        return itemService.updateItem(itemDto, id);
    }
}
