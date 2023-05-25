package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.model.ErrorMessage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

@SpringBootTest
@AutoConfigureMockMvc
public class GenericServiceTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected FilmDao filmDao;
    @Autowired
    protected GenreMpaDao genreMpaDao;
    protected static ObjectMapper objectMapper;
    protected User user1;
    protected User user2;
    protected User user3;
    protected Film film1;
    protected Film film2;
    protected String responseBody;
    @Autowired
    protected GenericMock<User> userGenericMock;
    @Autowired
    protected GenericMock<Film> filmGenericMock;
    @Autowired
    protected GenericMock<ErrorMessage> errorMessageMockGenericMock;


    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    protected User createUser(User user) throws Exception {
        return userGenericMock.postEntity("/users", user, User.class);
    }

    protected Film createFilm(Film film) throws Exception {
        return filmGenericMock.postEntity("/films", film, Film.class);
    }
}
