package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping()
    public Collection<Film> get() {
        log.info("get films response {}", films);
        return films.values();
    }

    @PostMapping
    @ResponseBody
    public Film create(@Valid @RequestBody Film film) {
        log.info("film create request {}", film);
        film.setId();
        films.put(film.getId(),film);
        log.info("film create response {}", film);
        return film;
    }

    @PutMapping
    @ResponseBody
    public Film update(@Valid @RequestBody Film film) {
        log.info("film update request {}", film);
        if (films.containsKey(film.getId())) {
            films.replace(film.getId(),film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}",film);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no such film");
    }
}
