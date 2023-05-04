package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.yandex.practicum.filmorate.model.ErrorMessage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.inmemory.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.inmemory.InMemoryUserStorage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FilmServiceTest extends GenericServiceTest {
    public FilmServiceTest(ApplicationContext applicationContext) throws Exception {
        super(applicationContext);
    }

    @BeforeEach
    void setUp() throws Exception {
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
        registry.destroySingleton("InMemoryFilmStorage");
    }

    @Test
    void getList() throws Exception {
        responseBody = mockMvc.perform(get("/films").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();
        List<Film> list = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(2, list.size());
    }

    @Test
    void getFilm() throws Exception {
        responseBody = mockMvc.perform(get("/films/" + film1.getId()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andReturn().getResponse().getContentAsString();

        Film film = objectMapper.readValue(responseBody, Film.class);
        assertEquals(film, film1);
    }

    @Test
    void getUnknownFilm() throws Exception {
        responseBody = mockMvc.perform(get("/films/9999").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.NOT_FOUND.value())).
                andReturn().getResponse().getContentAsString();

        ErrorMessage errorMessage = objectMapper.readValue(responseBody, ErrorMessage.class);
        assertEquals(errorMessage.getCause(), "no such filmId");
        assertEquals(errorMessage.getMessage(), "9999");
    }

    @Test
    void addLike() throws Exception {
        mockMvc.perform(put("/films/" + film1.getId() + "/like/" + user1.getId()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value()));

        assertEquals(user1.getId(), inMemoryFilmStorage.getRate(film1.getId()).getLikedUsersId().stream().findFirst().orElse(-1));
    }

    @Test
    void deleteLike() throws Exception {
        addLike();
        mockMvc.perform(delete("/films/" + film1.getId() + "/like/" + user1.getId()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value()));

        assertEquals(0, inMemoryFilmStorage.getRate(film1.getId()).getLikedUsersId().size());
    }

    @Test
    void getPopular() {
    }
}