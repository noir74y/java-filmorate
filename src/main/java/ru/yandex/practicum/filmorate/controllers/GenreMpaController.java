package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

@RestController
public class GenreMpaController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/genres")
    public Collection<Genre> listGenre() {
        return filmService.listGenre();
    }

    @GetMapping("/genres/{genreId}")
    public Genre getGenre(@PathVariable Integer genreId) {
        return filmService.getGenre(genreId);
    }

    @GetMapping("/mpa")
    public Collection<Mpa> listMpa() {
        return filmService.listMpa();
    }

    @GetMapping("/mpa/{mpaId}")
    public Mpa getMpa(@PathVariable Integer mpaId) {
        return filmService.getMpa(mpaId);
    }
}
