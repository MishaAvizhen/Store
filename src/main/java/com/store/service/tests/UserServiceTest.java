package com.store.service.tests;

import com.store.converter.impl.UserConverter;
import com.store.dto.UserRegistrationDto;
import com.store.entity.User;
import com.store.repository.UserRepository;
import com.store.service.impl.UserServiceImpl;
import com.store.service.testData.UserTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {
    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserConverter userConverter = new UserConverter();
    @InjectMocks
    private UserServiceImpl userService;

    private UserTestData userTestData = UserTestData.getInstance();

    @Before
    public void setUp() throws Exception {
        when(userRepository.findAll()).thenReturn(userTestData.getAllTestUsers());
        when(userRepository.findByUsername("user")).thenReturn(userTestData.getTestUserByUsername("user"));
        when(userRepository.save(any((User.class)))).thenAnswer(i -> userTestData.saveTestUser((User) i.getArguments()[0]));
   }

    @Test
    public void registerUser() throws Exception {

        String registerUsername = "registerUser";
        String registerPassword = "2";
        String registerMail = registerUsername + "@mail.ru";

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(registerUsername, registerPassword, registerMail);
        userService.registerUser(userRegistrationDto);
        User registeredUser = userTestData.getTestUserByUsername(registerUsername);
        Assert.assertNotNull(registeredUser);
        Assert.assertEquals(registeredUser.getUsername(), registerUsername);
    }

    @Test
    public void findUserByUsername() throws Exception {

        String expectedUserName = "user";
        User user = userService.findUserByUsername(expectedUserName);
        Assert.assertNotNull("User equals null", user);
        String actualUsername = user.getUsername();
        Assert.assertEquals("Username is not equals", expectedUserName, actualUsername);
    }

    @Test
    public void findAllUsers() throws Exception {
        List<User> expectedAllUsers = userTestData.getAllTestUsers();
        List<User> actualAllUsers = userService.findAllUsers();
        Assert.assertEquals(expectedAllUsers.size(), actualAllUsers.size());
    }
}