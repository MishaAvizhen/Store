package com.store.service.testData;

import com.store.entity.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemTestData {
    private Map<Long, Item> itemsForTest = new HashMap<>();
    private static ItemTestData INSTANCE = null;

    private ItemTestData() {
        initItemTestData();
    }

    public static ItemTestData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemTestData();
        }
        return INSTANCE;
    }

    private void initItemTestData() {
        saveTestItem(buildItemForTest(1L));
    }

    private Item buildItemForTest(Long id) {
        Item item = new Item();
        item.setId(id);
        item.setTitle("tea");
        item.setDescription("black");
        item.setTags("drinks");
        return item;
    }

    public List<Item> getAllTestItems() {
        return new ArrayList<>(itemsForTest.values());
    }

    public Item saveTestItem(Item item) {
        itemsForTest.put(item.getId(), item);
        return item;
    }

    public Item updateTestItem(Item testItem) {
        saveTestItem(testItem);
        return testItem;
    }

    public Item deleteTestItemById(Long itemId) {
        return itemsForTest.remove(itemId);
    }

    public Item getTestItemById(Long itemId) {
        for (Item item : itemsForTest.values()) {
            if (item.getId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }
}