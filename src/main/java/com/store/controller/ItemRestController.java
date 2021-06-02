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

import java.security.Principal;
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
    public ResponseEntity<String> removeFromCatalog(@PathVariable Long id) {
        itemService.delete(id);
        return new ResponseEntity<String>(" Item was deleted from catalog", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id,
                                             @RequestBody ItemDto itemDto, Principal principal) {

        itemService.updateItem(itemDto, id, principal.getName());
        return new ResponseEntity<String>("Item was updated", HttpStatus.OK);

    }

    @PutMapping("/forceUpdate/{id}")
    public ResponseEntity<String> forceUpdateItem(@PathVariable Long id,
                                                  @RequestBody ItemDto itemDto, Principal principal) {

        itemService.forceUpdateItem(itemDto, id, principal.getName());
        return new ResponseEntity<String>("Item was force updated", HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addItemToCatalog(@RequestBody ItemDto itemDto) {
        itemService.addItemToCatalog(itemDto);
        return new ResponseEntity<>("Item add to catalog", HttpStatus.OK);
    }
}
