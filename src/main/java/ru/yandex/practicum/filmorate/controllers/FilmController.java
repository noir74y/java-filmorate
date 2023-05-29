package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping()
    public Collection<Film> list() {
        return filmService.list();
    }

    @GetMapping("/{filmId}")
    public Film get(@PathVariable Integer filmId) {
        return filmService.get(filmId);
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        return filmService.create(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        film = filmService.update(film);
        return film;
    }

    @PutMapping("/{filmId}/like/{userId}")
    public void addLike(@PathVariable Integer filmId, @PathVariable Integer userId) {
        filmService.addLike(filmId, userId);
    }

    @DeleteMapping("/{filmId}/like/{userId}")
    public void deleteLike(@PathVariable Integer filmId, @PathVariable Integer userId) {
        filmService.deleteLike(filmId, userId);
    }

    @GetMapping("/popular")
    public Collection<Film> listPopular(@RequestParam(required = false, defaultValue = "10") Integer count) {
        return filmService.listPopular(count);
    }
}
