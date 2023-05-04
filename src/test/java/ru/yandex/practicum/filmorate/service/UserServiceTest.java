package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.yandex.practicum.filmorate.model.ErrorMessage;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserServiceTest extends GenericServiceTest {
    @BeforeEach
    void setUp() throws Exception {
        inMemoryUserStorage.clear();

        user1 = getUserFromMock(User.builder().
                login("dolore").
                name("Nick Name").
                email("mail@mail.ru").
                birthday(LocalDate.of(1946, 8, 20)).
                build());

        user2 = getUserFromMock(User.builder().
                login("friend").
                name("friend adipisicing").
                email("friend@mail.ru").
                birthday(LocalDate.of(1976, 8, 20)).
                build());

        user3 = getUserFromMock(User.builder().
                login("common").
                name("").
                email("friend@common.ru").
                birthday(LocalDate.of(2000, 8, 20)).
                build());
    }

    @Test
    void getList() throws Exception {
        responseBody = mockMvc.perform(get("/users").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();
        List<User> list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        responseBody = mockMvc.perform(get("/users").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();
        list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(3, list.size());
    }

    @Test
    void getUser() throws Exception {

        responseBody = mockMvc.perform(get("/users/" + user1.getId()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        User user = objectMapper.readValue(responseBody, User.class);
        assertEquals(user, user1);
    }

    @Test
    void getUnknownUser() throws Exception {
        responseBody = mockMvc.perform(get("/users/9999").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.NOT_FOUND.value())).
                andReturn().getResponse().getContentAsString();

        ErrorMessage errorMessage = objectMapper.readValue(responseBody, ErrorMessage.class);
        assertEquals(errorMessage.getCause(), "no such userId");
        assertEquals(errorMessage.getMessage(), "9999");
    }

    @Test
    void addFriendship() throws Exception {
        mockMvc.perform(put("/users/" + user1.getId() + " /friends/" + user2.getId()));

        responseBody = mockMvc.perform(get("/users/" + user1.getId() + "/friends").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        List<User> list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(1, list.size());
        assertEquals(user2, list.get(0));

        responseBody = mockMvc.perform(get("/users/" + user2.getId() + "/friends").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(1, list.size());
        assertEquals(user1, list.get(0));
    }

    @Test
    void deleteFriendship() throws Exception {
        addFriendship();
        mockMvc.perform(delete("/users/" + user1.getId() + " /friends/" + user2.getId()));

        responseBody = mockMvc.perform(get("/users/" + user1.getId() + "/friends").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        List<User> list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(0, list.size());
    }

    @Test
    void getCommonFriends() throws Exception {
        mockMvc.perform(put("/users/" + user1.getId() + " /friends/" + user2.getId()));
        mockMvc.perform(put("/users/" + user1.getId() + " /friends/" + user3.getId()));

        responseBody = mockMvc.perform(get("/users/" + user2.getId() + "/friends/common/" + user3.getId()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        List<User> list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(1, list.size());
        assertEquals(list.get(0), user1);
    }
}