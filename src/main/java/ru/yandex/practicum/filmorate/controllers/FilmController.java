package ru.yandex.practicum.filmorate.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping()
    public Collection<Film> findAll() {
        return films.values();
    }

    @PostMapping
    @ResponseBody
    public Film create(@Valid @RequestBody Film film) {
        film.setId();
        films.put(film.getId(),film);
        return film;
    }

    @PutMapping
    @ResponseBody
    public Film update(@Valid @RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            films.replace(film.getId(),film);
            return film;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no such film");
    }
}
