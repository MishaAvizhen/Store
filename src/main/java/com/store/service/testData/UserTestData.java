package com.store.service.testData;

import com.store.constants.Role;
import com.store.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTestData {
    private Map<String, User> usersForTest = new HashMap<>();
    private static UserTestData INSTANCE = null;

    private UserTestData() {
        initUserTestData();
    }

    public static UserTestData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserTestData();
        }
        return INSTANCE;
    }

    private void initUserTestData() {
        saveTestUser(buildUserWithUserName("user", 1L));
        saveTestUser(buildUserWithUserName("user1", 2L));
    }

    private User buildUserWithUserName(String username, Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setRole(Role.USER);
        user.setEmail(username + "@mail.ru");
        user.setPassword("2");
        return user;
    }

    public List<User> getAllTestUsers() {
        return new ArrayList<>(usersForTest.values());
    }

    public User getTestUserByUsername(String username) {
        return usersForTest.get(username);
    }


    public User saveTestUser(User user) {
        usersForTest.put(user.getUsername(), user);
        return user;
    }


}