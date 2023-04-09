package ru.yandex.practicum.filmorate.controllers;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.LinkedHashSet;
import java.util.Set;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final Set<Film> films = new LinkedHashSet<>();

    @GetMapping()
    public Set<Film> findAll() {
        return films;
    }

    @PostMapping()
    @ResponseBody
    public Film create(@Valid @RequestBody Film film) {
        films.add(film);
        return film;
    }

    @PutMapping()
    @ResponseBody
    public Film update(@Valid @RequestBody Film film) {
        films.add(film);
        return film;
    }
}
