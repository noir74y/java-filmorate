package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.inmemory.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.inmemory.InMemoryUserStorage;

import java.time.Duration;
import java.time.LocalDate;

class FilmServiceTest extends GenericServiceTest {
    @BeforeEach
    void setUp() throws Exception {
        inMemoryUserStorage = applicationContext.getBean(InMemoryUserStorage.class);
        inMemoryFilmStorage = applicationContext.getBean(InMemoryFilmStorage.class);

        film1 = getFilmFromMock(Film.builder().
                name("Nick Name").
                description("adipisicing").
                releaseDate(LocalDate.of(1967, 3, 25)).
                duration(Duration.ofMinutes(100)).
                build());

        film2 = getFilmFromMock(Film.builder().
                name("New film").
                description("New film about friends").
                releaseDate(LocalDate.of(1999, 4, 30)).
                duration(Duration.ofMinutes(120)).
                build());

        user1 = getUserFromMock(User.builder().
                login("dolore").
                name("Nick Name").
                email("mail@mail.ru").
                birthday(LocalDate.of(1946, 8, 20)).
                build());
    }

    @AfterEach
    void tearDown() {
        registry = (DefaultSingletonBeanRegistry) applicationContext.getAutowireCapableBeanFactory();
        registry.destroySingleton("InMemoryFilmStorage");
        registry.destroySingleton("InMemoryUserStorage");
    }

    @Test
    void addLike() {
    }

    @Test
    void deleteLike() {
    }

    @Test
    void getPopular() {
    }
}