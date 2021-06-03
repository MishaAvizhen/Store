package com.store.controllers;

import com.store.converter.impl.ItemConverter;
import com.store.dto.FilteredItemsDto;
import com.store.dto.ItemDto;
import com.store.entity.Item;
import com.store.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/items")
@Api(tags = " Item controller", description = " Operations with item ")

public class ItemRestController {
    private ItemService itemService;
    private ItemConverter itemConverter;

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    @ApiOperation(value = "Get all items")
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(value = "description", required = false) String description,
                                                  @RequestParam(value = "tags", required = false) String tags,
                                                  @RequestParam(value = "title", required = false) String title) {
        FilteredItemsDto filteredItemsDto = new FilteredItemsDto(description, tags, title);
        List<Item> items = itemService.filteredItem(filteredItemsDto);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete item")
    public ResponseEntity<String> removeFromCatalog(@PathVariable Long id) {
        itemService.delete(id);
        return new ResponseEntity<String>(" Item was deleted from catalog", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update item")
    public ResponseEntity<String> updateItem(@PathVariable Long id,
                                             @RequestBody ItemDto itemDto, Principal principal) {

        itemService.updateItem(itemDto, id, principal.getName());
        return new ResponseEntity<String>("Item was updated", HttpStatus.OK);
    }

    @PutMapping("/forceUpdate/{id}")
    @ApiOperation(value = "Force update item")
    public ResponseEntity<String> forceUpdateItem(@PathVariable Long id,
                                                  @RequestBody ItemDto itemDto, Principal principal) {

        itemService.forceUpdateItem(itemDto, id, principal.getName());
        return new ResponseEntity<String>("Item was force updated", HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Add item to catalog")
    public ResponseEntity<String> addItemToCatalog(@RequestBody ItemDto itemDto) {
        itemService.addItemToCatalog(itemDto);
        return new ResponseEntity<>("Item add to catalog", HttpStatus.OK);
    }
}
