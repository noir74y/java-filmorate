package ru.yandex.practicum.filmorate.inmemory;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.interfaces.FilmStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    @Override
    public Collection<Film> get() {
        return null;
    }

    @Override
    public Film create(Film film) {
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
    }
}
