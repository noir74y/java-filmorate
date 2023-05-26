package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import ru.yandex.practicum.filmorate.model.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmServiceTest extends GenericServiceTest {
    @BeforeEach
    void setUp() throws Exception {
        filmDao.clear();
        userDao.clear();

        film1 = createFilm(Film.builder()
                .name("Nick Name")
                .description("adipisicing")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(Duration.ofMinutes(100))
                .mpa(new Mpa(5, "NC-17"))
                .genres(new HashSet<>(List.of(new Genre(4,"Триллер"),new Genre(2,"Драма"))))
                .build());

        film2 = createFilm(Film.builder()
                .name("New film")
                .description("New film about friends")
                .releaseDate(LocalDate.of(1999, 4, 30))
                .duration(Duration.ofMinutes(120))
                .mpa(new Mpa(2, "PG"))
                .genres(new HashSet<>(List.of(new Genre(2,"Драма"))))
                .build());

        user1 = createUser(User.builder()
                .login("dolore")
                .name("Nick Name")
                .email("mail@mail.ru")
                .birthday(LocalDate.of(1946, 8, 20))
                .build());
    }

    @Test
    void getList() throws Exception {
        List<Film> list = objectMapper.readValue(filmGenericMock.listEntityJson("/films"), new TypeReference<>() {
        });
        assertEquals(2, list.size());
    }

    @Test
    void getFilm() throws Exception {
        Film film = filmGenericMock.getEntity("/films/" + film1.getId(), Film.class);
        assertEquals(film, film1);
    }

    @Test
    void getUnknownFilm() throws Exception {
        ErrorMessage errorMessage = errorMessageMockGenericMock.getEntity("/films/9999", HttpStatus.NOT_FOUND.value(), ErrorMessage.class);
        assertEquals(errorMessage.getCause(), "no such filmId");
        assertEquals(errorMessage.getMessage(), "9999");
    }

    @Test
    void addLike() throws Exception {
        filmGenericMock.putEntity("/films/" + film1.getId() + "/like/" + user1.getId());
        assertEquals(user1.getId(), filmDao.getFilmLikes(film1.getId()).getLikedUsersId().stream().findFirst().orElse(-1));
    }

    @Test
    void deleteLike() throws Exception {
        addLike();
        filmGenericMock.deleteEntity("/films/" + film1.getId() + "/like/" + user1.getId());
        assertEquals(0, filmDao.getFilmLikes(film1.getId()).getLikedUsersId().size());
    }

    @Test
    void getPopular() throws Exception {
        filmGenericMock.putEntity("/films/" + film2.getId() + "/like/" + user1.getId());
        List<Film> list = objectMapper.readValue(filmGenericMock.listEntityJson("/films/popular?count=1"), new TypeReference<>() {
        });
        assertEquals(film2, list.get(0));
    }

    @Test
    void updateFilm() throws Exception {
        film1.setName("new name");
        film1.setDuration(Duration.ofSeconds(500));
        Film film = filmGenericMock.putEntity("/films", film1, Film.class);
        assertEquals(film, film1);
    }
}