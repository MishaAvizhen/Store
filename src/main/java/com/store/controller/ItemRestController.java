package com.store.controller;

import com.store.converter.impl.ItemConverter;
import com.store.dto.FilteredItemsDto;
import com.store.dto.ItemDto;
import com.store.entity.Item;
import com.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(value = "description", required = false) String description,
                                  @RequestParam(value = "tags", required = false) String tags,
                                  @RequestParam(value = "title", required = false) String title) {
        FilteredItemsDto filteredItemsDto = new FilteredItemsDto(description, tags, title);
        List<Item> items = itemService.filteredItem(filteredItemsDto);
        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long id) {
        itemService.delete(id);
        return new ResponseEntity<String>(" Item was deleted from cart", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id,
                           @RequestBody ItemDto itemDto) {
        Item item = itemService.updateItem(itemDto, id);
        return new ResponseEntity<Item>(item, HttpStatus.OK);

    }

    @PostMapping
    public  ResponseEntity<Item> addItemToCatalog(@RequestBody ItemDto itemDto) {
        Item item = itemService.addItemToCatalog(itemDto);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
}
