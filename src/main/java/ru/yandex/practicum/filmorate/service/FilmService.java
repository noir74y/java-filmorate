package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FilmService {
    private final Map<Integer, Film> films = new HashMap<>();

    public Collection<Film> get() {
        log.info("get films response {}", films);
        return films.values();
    }

    public Film create(Film film) {
        log.info("film create request {}", film);
        film.setId();
        films.put(film.getId(),film);
        log.info("film create response {}", film);
        return film;
    }

    public Film update(Film film) {
        log.info("film update request {}", film);
        if (films.containsKey(film.getId())) {
            films.replace(film.getId(),film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}",film);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no such film");
    }
}
