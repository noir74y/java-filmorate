package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmController {
    private final FilmStorage filmStorage;

    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping()
    public Collection<Film> get() {
        return filmStorage.get();
    }

    @PostMapping
    @ResponseBody
    public Film create(@Valid @RequestBody Film film) {
        return filmStorage.create(film);
    }

    @PutMapping
    @ResponseBody
    public Film update(@Valid @RequestBody Film film) {
        return filmStorage.update(film);
    }
}
