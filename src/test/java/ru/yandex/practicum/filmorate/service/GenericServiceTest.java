package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.model.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GenericServiceTest {
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected FilmDao filmDao;
    protected static ObjectMapper objectMapper;
    protected User user1;
    protected User user2;
    protected User user3;
    protected Film film1;
    protected Film film2;
    @Autowired
    protected GenericMock<User> userMock;
    @Autowired
    protected GenericMock<Film> filmMock;
    @Autowired
    protected GenericMock<ErrorMessage> errorMessageMock;
    @Autowired
    protected GenericMock<Mpa> mpaMock;
    @Autowired
    protected GenericMock<Genre> genreMock;

    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    protected User createUser(User user) throws Exception {
        return userMock.postEntity("/users", user, User.class);
    }

    protected Film createFilm(Film film) throws Exception {
        return filmMock.postEntity("/films", film, Film.class);
    }
}
