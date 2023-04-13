package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping()
    public Collection<Film> get() {
        return filmService.get();
    }

    @PostMapping
    @ResponseBody
    public Film create(@Valid @RequestBody Film film) {
        return filmService.create(film);
    }

    @PutMapping
    @ResponseBody
    public Film update(@Valid @RequestBody Film film) {
        return filmService.update(film);
    }
}
