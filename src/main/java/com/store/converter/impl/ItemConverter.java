package com.store.converter.impl;

import com.store.converter.Converter;
import com.store.dto.ItemDto;
import com.store.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter implements Converter<Item, ItemDto> {

    @Override
    public Item convertToEntity(ItemDto itemDto) {
        Item item = new Item();
        item.setDescription(itemDto.getDescription());
        item.setTitle(itemDto.getTitle());
        item.setTags(itemDto.getTags());
        return item;

    }

    @Override
    public Item convertEntityToUpdate(ItemDto dto, Item entity) {
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setTags(dto.getTags());
        return entity;
    }
}
