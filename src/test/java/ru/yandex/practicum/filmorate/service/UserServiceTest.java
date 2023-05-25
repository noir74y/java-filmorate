package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.yandex.practicum.filmorate.model.ErrorMessage;
import ru.yandex.practicum.filmorate.model.User;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserServiceTest extends GenericServiceTest {
    @BeforeEach
    void setUp() throws Exception {
        userDao.clear();

        user1 = createUser(User.builder()
                .login("dolore")
                .name("Nick Name")
                .email("mail@mail.ru")
                .birthday(LocalDate.of(1946, 8, 20))
                .build());

        user2 = createUser(User.builder()
                .login("friend")
                .name("friend adipisicing")
                .email("friend@mail.ru")
                .birthday(LocalDate.of(1976, 8, 20))
                .build());

        user3 = createUser(User.builder()
                .login("common")
                .name("")
                .email("friend@common.ru")
                .birthday(LocalDate.of(2000, 8, 20))
                .build());
    }

    @Test
    void getList() throws Exception {
        responseBody = mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        responseBody = mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        List<User> list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(3, list.size());
    }

    @Test
    void getUser() throws Exception {

        responseBody = mockMvc.perform(get("/users/" + user1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        User user = objectMapper.readValue(responseBody, User.class);
        assertEquals(user, user1);
    }

    @Test
    void getUnknownUser() throws Exception {
        responseBody = mockMvc.perform(get("/users/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        ErrorMessage errorMessage = objectMapper.readValue(responseBody, ErrorMessage.class);
        assertEquals(errorMessage.getCause(), "no such userId");
        assertEquals(errorMessage.getMessage(), "9999");
    }

    @Test
    void addFriend() throws Exception {
        mockMvc.perform(put("/users/" + user1.getId() + " /friends/" + user2.getId()));

        responseBody = mockMvc.perform(get("/users/" + user1.getId() + "/friends")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        List<User> list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(1, list.size());
        assertEquals(user2, list.get(0));
    }

    @Test
    void deleteFriend() throws Exception {
        addFriend();
        mockMvc.perform(delete("/users/" + user1.getId() + " /friends/" + user2.getId()));

        responseBody = mockMvc.perform(get("/users/" + user1.getId() + "/friends")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        List<User> list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(0, list.size());
    }

    @Test
    void getCommonFriends() throws Exception {
        mockMvc.perform(put("/users/" + user1.getId() + " /friends/" + user3.getId()));
        mockMvc.perform(put("/users/" + user2.getId() + " /friends/" + user3.getId()));

        responseBody = mockMvc.perform(get("/users/" + user1.getId() + "/friends/common/" + user2.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        List<User> list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(1, list.size());
        assertEquals(list.get(0), user3);
    }

    @Test
    void updateUser() throws Exception {
        user1.setName("new name");
        user1.setLogin("new_login");

        responseBody = mockMvc.perform(put("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        User user = objectMapper.readValue(responseBody, User.class);
        assertEquals(user, user1);
    }
}