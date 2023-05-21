package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@RestController
public class GenreMpaController {
    @Autowired
    private GenreMpaDao genreMpaDao;

    @GetMapping("/genres")
    public Collection<Genre> listGenre() {
        return genreMpaDao.listGenre();
    }

    @GetMapping("/genres/{genreId}")
    public Genre getGenre(@PathVariable Integer genreId) {
        return genreMpaDao.getGenre(genreId);
    }

    @GetMapping("/mpa")
    public Collection<Mpa> listMpa() {
        return genreMpaDao.listMpa();
    }

    @GetMapping("/mpa/{mpaId}")
    public Mpa getMpa(@PathVariable Integer mpaId) {
        return genreMpaDao.getMpa(mpaId);
    }
}
