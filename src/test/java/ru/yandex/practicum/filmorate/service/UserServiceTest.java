package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import ru.yandex.practicum.filmorate.model.ErrorMessage;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        List<User> list = objectMapper.readValue(userMock.listEntityJson("/users"), new TypeReference<>() {
        });
        assertEquals(3, list.size());
    }

    @Test
    void getUser() throws Exception {
        User user = userMock.getEntity("/users/" + user1.getId(), User.class);
        assertEquals(user, user1);
    }

    @Test
    void getUnknownUser() throws Exception {
        ErrorMessage errorMessage = errorMessageMock.getEntity("/users/9999", HttpStatus.NOT_FOUND.value(), ErrorMessage.class);
        assertEquals(errorMessage.getCause(), "no such userId");
        assertEquals(errorMessage.getMessage(), "9999");
    }

    @Test
    void addFriend() throws Exception {
        userMock.putEntity("/users/" + user1.getId() + " /friends/" + user2.getId());
        List<User> list = objectMapper.readValue(userMock.listEntityJson("/users/" + user1.getId() + "/friends"), new TypeReference<>() {
        });
        assertEquals(1, list.size());
        assertEquals(user2, list.get(0));
    }

    @Test
    void deleteFriend() throws Exception {
        addFriend();
        userMock.deleteEntity("/users/" + user1.getId() + " /friends/" + user2.getId());
        List<User> list = objectMapper.readValue(userMock.listEntityJson("/users/" + user1.getId() + "/friends"), new TypeReference<>() {
        });
        assertEquals(0, list.size());
    }

    @Test
    void getCommonFriends() throws Exception {
        userMock.putEntity("/users/" + user1.getId() + " /friends/" + user3.getId());
        userMock.putEntity("/users/" + user2.getId() + " /friends/" + user3.getId());
        List<User> list = objectMapper.readValue(userMock.listEntityJson("/users/" + user1.getId() + "/friends/common/" + user2.getId()), new TypeReference<>() {
        });
        assertEquals(1, list.size());
        assertEquals(list.get(0), user3);
    }

    @Test
    void updateUser() throws Exception {
        user1.setName("new name");
        user1.setLogin("new_login");
        User user = userMock.putEntity("/users",user1, User.class);
        assertEquals(user, user1);
    }
}