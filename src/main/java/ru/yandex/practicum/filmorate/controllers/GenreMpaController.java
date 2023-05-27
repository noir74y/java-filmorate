package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.GenreMpaService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class GenreMpaController {
    private final GenreMpaService genreMpaService;

    @GetMapping("/genres")
    public Collection<Genre> listGenre() {
        return genreMpaService.listGenre();
    }

    @GetMapping("/genres/{genreId}")
    public Genre getGenre(@PathVariable Integer genreId) {
        return genreMpaService.getGenre(genreId);
    }

    @GetMapping("/mpa")
    public Collection<Mpa> listMpa() {
        return genreMpaService.listMpa();
    }

    @GetMapping("/mpa/{mpaId}")
    public Mpa getMpa(@PathVariable Integer mpaId) {
        return genreMpaService.getMpa(mpaId);
    }
}
