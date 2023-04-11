package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controllers.FilmController;
import ru.yandex.practicum.filmorate.model.Film;

import javax.swing.*;
import javax.validation.ConstraintViolation;
import javax.xml.validation.Validator;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@SpringBootTest
public class FilmControllerTest {
//    private static FilmController filmController;
//    private static Film film;
//    private Validator validator;
//
//    @BeforeEach
//    void setUp() {
//        filmController = new FilmController();
//        film = Film.builder().
//                name("film").
//                description("description").
//                releaseDate(LocalDate.of(1969,3,25)).
//                duration(Duration.of(100, ChronoUnit.SECONDS)).
//                build();
//    }
//
//    @Test
//    void shouldNotAddFilmWithEmptyName() {
//        film.setName(null);
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//    }

}
