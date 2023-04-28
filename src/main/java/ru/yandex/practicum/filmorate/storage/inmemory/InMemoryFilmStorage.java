package ru.yandex.practicum.filmorate.storage.inmemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private final Map<Integer, Set<Integer>> likes = new HashMap<>();

    @Override
    public Collection<Film> get() {
        log.info("get films response {}", films);
        return films.values();
    }

    @Override
    public Film create(Film film) {
        log.info("film create request {}", film);
        film.setId();
        films.put(film.getId(), film);
        log.info("film create response {}", film);
        return film;
    }

    @Override
    public Film update(Film film) {
        log.info("film update request {}", film);
        if (films.containsKey(film.getId())) {
            films.replace(film.getId(), film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}", film);
        throw new NotFoundException("no such user");
    }
}
