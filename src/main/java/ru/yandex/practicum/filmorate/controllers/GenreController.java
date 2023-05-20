package ru.yandex.practicum.filmorate.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;


@RestController
@RequestMapping("/genres")
public class GenreController {

    @GetMapping()
    public Collection<Genre> list() {
        return null;
    }

    @GetMapping("/{genreId}")
    public Genre get(@PathVariable Integer genreId) {
        return null;
    }
}
