package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.yandex.practicum.filmorate.controllers.UserController;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTest {
//    private UserController userController;
//    private User user;
//
//    @BeforeEach
//    void beforeEach() {
//        userController = new UserController();
//        user = new User( 0, "mail@mail.ru", "dolore", "Nick Name", LocalDate.of(1895, 12, 28));
//    }
//
//    @Test
//    void shouldCreateUser() {
//        userController.create(user);
//        Collection<User> listUser = userController.get();
//        assertEquals(1, listUser.size(), "Number of users isn't correct");
//        assertEquals(user.getEmail(), listUser.get(0).getEmail(), "User's email isn't correct");
//        assertEquals(user.getName(), listUser.get(0).getName(), "User's name isn't correct");
//        assertEquals(user.getLogin(), listUser.get(0).getLogin(), "User's login isn't correct");
//        assertEquals(user.getBirthday(), listUser.get(0).getBirthday(), "User's name isn't correct");
//    }
//
//    @Test
//    void shouldNotCreateUserWithFailEmail() {
//        User failedUser = new User(0, "mail.ru", "dolore", "Nick Name", LocalDate.of(1895, 12, 28));
//        userController.create(failedUser);
//        Collection<User> listUser = userController.get();
//        System.out.println(failedUser);
//        assertEquals(1, listUser.size(), "User's email isn't correct");
//    }
}
