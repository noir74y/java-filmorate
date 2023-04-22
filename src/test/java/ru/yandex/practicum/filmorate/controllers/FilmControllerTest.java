package ru.yandex.practicum.filmorate.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ConstraintViolation;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

class FilmControllerTest extends GenericControllerTest {
    private Film film;

    @Test
    void checkValidFilm() throws Exception {
        film = Film.builder()
                .name("Nick Name")
                .description("mail@mail.ru")
                .releaseDate(LocalDate.now())
                .duration(Duration.of(120, ChronoUnit.MINUTES))
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals(0, violations.size());
    }

    @Test
    void checkInValidFilm() throws Exception {
        film = Film.builder()
                .description("Пятеро друзей ( комик-группа «Шарло»), приезжают в город Бризуль. Здесь они хотят разыскать господина Огюста Куглова, который задолжал им деньги, а именно 20 миллионов. о Куглов, который за время «своего отсутствия», стал кандидатом Коломбани.")
                .releaseDate(LocalDate.of(1895, 12, 27))
                .duration(Duration.of(-1, ChronoUnit.SECONDS))
                .build();

        violations = validator.validate(film);

        assertEquals(4, violations.size());
        assertTrue(isConstraintTriggered(violations, "название не может быть пустым"));
        assertTrue(isConstraintTriggered(violations, "максимальная длина описания — 200 символов"));
        assertTrue(isConstraintTriggered(violations, "дата релиза — не раньше 28 декабря 1895 года"));
        assertTrue(isConstraintTriggered(violations, "продолжительность фильма должна быть положительной"));
    }
}