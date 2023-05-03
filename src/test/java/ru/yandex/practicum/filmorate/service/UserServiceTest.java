package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.model.ErrorMessage;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.inmemory.InMemoryUserStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserServiceTest {
    @Autowired
    private final ApplicationContext applicationContext;
    @Autowired
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;
    InMemoryUserStorage inMemoryUserStorage;
    private User user1;
    private User user2;
    private User user3;
    String responseBody;

    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void setUp() {
        inMemoryUserStorage = applicationContext.getBean(InMemoryUserStorage.class);
        UserService userService = applicationContext.getBean(UserService.class);

        user1 = User.builder().
                login("dolore").
                name("Nick Name").
                email("mail@mail.ru").
                birthday(LocalDate.of(1946, 8, 20)).
                build();

        user2 = User.builder().
                login("friend").
                name("friend adipisicing").
                email("friend@mail.ru").
                birthday(LocalDate.of(1976, 8, 20)).
                build();

        user3 = User.builder().
                login("common").
                name("").
                email("friend@common.ru").
                birthday(LocalDate.of(2000, 8, 20)).
                build();

        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
    }

    @AfterEach
    void tearDown() {
        DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) applicationContext.getAutowireCapableBeanFactory();
        registry.destroySingleton("UserService");
        registry.destroySingleton("InMemoryUserStorage");
    }

    @Test
    void getUser() throws Exception {

        responseBody = mockMvc.perform(get("/users/" + user1.getId()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        User userFromJson = objectMapper.readValue(responseBody, User.class);

        assertEquals(userFromJson.getId(), user1.getId());
        assertEquals(userFromJson.getEmail(), user1.getEmail());
        assertEquals(userFromJson.getName(), user1.getName());
        assertEquals(userFromJson.getBirthday(), user1.getBirthday());
    }

    @Test
    void getUnknownUser() throws Exception {
        responseBody = mockMvc.perform(get("/users/9999").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.NOT_FOUND.value())).
                andReturn().getResponse().getContentAsString();

        ErrorMessage errorMessageFromJson = objectMapper.readValue(responseBody, ErrorMessage.class);
        assertEquals(errorMessageFromJson.getCause(), "no such userId");
        assertEquals(errorMessageFromJson.getMessage(), "9999");
    }

    @Test
    void addFriendship() throws Exception {
        mockMvc.perform(put("/users/" + user1.getId() + " /friends/" + user2.getId()));

        responseBody = mockMvc.perform(get("/users/" + user1.getId() + "/friends").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        List<User> friendsList = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(1, friendsList.size());
    }

    @Test
    void deleteFriendship() throws Exception {
    }

    @Test
    void getCommonFriends() throws Exception {
    }

    @Test
    void getFriends() throws Exception {
    }

}