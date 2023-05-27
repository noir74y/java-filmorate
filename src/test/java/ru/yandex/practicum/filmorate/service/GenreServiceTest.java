package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreServiceTest extends GenericServiceTest {
    @Test
    void getListGenre() throws Exception {
        List<Genre> list = objectMapper.readValue(mpaMock.listEntityJson("/genres"), new TypeReference<>() {
        });
        assertEquals(6, list.size());
    }

    @Test
    void getGenre() throws Exception {
        Genre genre1 = Genre.builder().id(1).name("Комедия").build();
        Genre genre2 = genreMock.getEntity("/genres/1", Genre.class);
        assertEquals(genre1, genre2);
    }

}
