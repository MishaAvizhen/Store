package com.store.service.tests;

import com.store.cart.CartsManager;
import com.store.converter.impl.ItemConverter;
import com.store.dto.FilteredItemsDto;
import com.store.dto.ItemDto;
import com.store.entity.Item;
import com.store.entity.User;
import com.store.repository.ItemRepository;
import com.store.repository.UserRepository;
import com.store.service.impl.ItemServiceImpl;
import com.store.service.impl.UserServiceImpl;
import com.store.service.testData.ItemTestData;
import com.store.service.testData.UserTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private CartsManager cartsManager;
    @InjectMocks
    private ItemServiceImpl itemService;
    @Spy
    private ItemConverter itemConverter = new ItemConverter();
    private ItemTestData itemTestData = ItemTestData.getInstance();

    @Mock
    private UserRepository userRepository;
    private UserTestData userTestData = UserTestData.getInstance();
    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        when(itemRepository.findAll()).thenReturn(itemTestData.getAllTestItems());
        when(itemRepository.saveAndFlush(any((Item.class)))).thenAnswer(i -> itemTestData.updateTestItem((Item) i.getArguments()[0]));
        when(itemRepository.findById(any(Long.class))).thenAnswer(i -> Optional.ofNullable(itemTestData.getTestItemById((Long) i.getArguments()[0])));
        doAnswer(i -> itemTestData.deleteTestItemById((Long) i.getArguments()[0])).when(itemRepository).deleteById(any(Long.class));
    }

    @Test
    public void filteredItem() throws Exception {
        FilteredItemsDto filteredItemsDto = new FilteredItemsDto();
        List<Item> actualItems = itemTestData.getAllTestItems();
        List<Item> expectedItems = itemService.filteredItem(filteredItemsDto);
        Assert.assertEquals(expectedItems.size(), actualItems.size());
    }

    @Test
    public void findById() throws Exception {
        Long itemId = 1L;
        Item expectedItem = itemService.findById(itemId);
        Assert.assertNotNull("Items equals null", expectedItem);
        Item actualItem = itemTestData.getTestItemById(itemId);
        Assert.assertEquals("items is not equals", expectedItem, actualItem);
    }

    @Test
    public void delete() throws Exception {
        Long itemId = 1L;
        itemService.delete(itemId);
        Item itemAfterDelete = itemTestData.getTestItemById(1L);
        Assert.assertNull("Item was not delete", itemAfterDelete);
    }

    @Test
    public void updateItem() throws Exception {
        String username = "user";
        long itemId = 1L;
        Item actualItem = itemTestData.getTestItemById(itemId);
        String actualTitle = actualItem.getTitle();
        String actualDescription = actualItem.getDescription();
        String actualTags = actualItem.getTags();
        when(userRepository.findByUsername(username)).thenReturn(userTestData.getTestUserByUsername(username));
        String title = "updateTitle";
        String description = "updateDescription";
        String tags = "updateTags";
        userService.findUserByUsername(username);
        ItemDto itemDto = new ItemDto(title, description, tags);
        Item expectedItem = itemService.updateItem(itemDto, itemId, username);
        String expectedTitle = expectedItem.getTitle();
        String expectedDescription = expectedItem.getDescription();
        String expectedTags = expectedItem.getTags();

        Assert.assertNotEquals("title wasn't update", expectedTitle, actualTitle);
        Assert.assertNotEquals("description wasn't update", expectedDescription, actualDescription);
        Assert.assertNotEquals("tags wasn't update", expectedTags, actualTags);
    }
}