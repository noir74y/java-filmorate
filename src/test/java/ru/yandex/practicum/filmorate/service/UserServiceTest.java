package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
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
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = applicationContext.getBean(UserService.class);
    }

    @AfterEach
    void tearDown() {
        DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) applicationContext.getAutowireCapableBeanFactory();
        registry.destroySingleton("UserService");
    }


    @Test
    void addFriendship() throws Exception {


        User user = User.builder().
                login("dolore").
                name("Nick Name").
                email("mail@mail.ru").
                birthday(LocalDate.of(1946, 8, 20)).
                build();

        userService.create(user);

        String responseBody = mockMvc.perform(post("/users").
                        content(objectMapper.writeValueAsString(user)).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        System.out.println(responseBody);
        System.out.println(userService.list().size());

//                andExpect(jsonPath("$.id").value(1)).
//                andExpect(jsonPath("$.login").value("dolore")).
//                andExpect(jsonPath("$.email").value("mail@mail.ru")).
//                andExpect(jsonPath("$.birthday").value("1946-08-20"));
    }

    @Test
    void deleteFriendship() throws Exception {
        User user = User.builder().
                login("dolore").
                name("Nick Name").
                email("mail@mail.ru").
                birthday(LocalDate.of(1946, 8, 20)).
                build();

        userService.create(user);

        String responseBody = mockMvc.perform(post("/users").
                        content(objectMapper.writeValueAsString(user)).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        System.out.println(responseBody);
        System.out.println(userService.list().size());
    }

    @Test
    void getCommonFriends() throws Exception {
        User user = User.builder().
                login("dolore").
                name("Nick Name").
                email("mail@mail.ru").
                birthday(LocalDate.of(1946, 8, 20)).
                build();

        userService.create(user);

        String responseBody = mockMvc.perform(post("/users").
                        content(objectMapper.writeValueAsString(user)).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        System.out.println(responseBody);
        System.out.println(userService.list().size());
    }

    @Test
    void getFriends() throws Exception {
        User user = User.builder().
                login("dolore").
                name("Nick Name").
                email("mail@mail.ru").
                birthday(LocalDate.of(1946, 8, 20)).
                build();

        userService.create(user);

        String responseBody = mockMvc.perform(post("/users").
                        content(objectMapper.writeValueAsString(user)).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        System.out.println(responseBody);
        System.out.println(userService.list().size());
    }
}