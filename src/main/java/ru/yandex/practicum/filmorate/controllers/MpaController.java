package ru.yandex.practicum.filmorate.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@RestController
@RequestMapping("/mpa")
public class MpaController {
    @GetMapping()
    public Collection<Mpa> list() {
        return null;
    }

    @GetMapping("/{genreId}")
    public Mpa get(@PathVariable Integer mpsId) {
        return null;
    }
}
