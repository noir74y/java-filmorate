package ru.yandex.practicum.filmorate.storage.inmemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Like;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private final Map<Integer, Like> likes = new HashMap<>();

    @Override
    public Collection<Film> list() {
        log.info("get films response {}", films);
        return films.values();
    }

    @Override
    public Film get(Integer filmId) {
        return films.get(filmId);
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


    @Override
    public void addLike(Integer filmId, Integer userId) {
        Like filmLike = likes.getOrDefault(filmId, new Like(filmId, new HashSet<>()));
        filmLike.getUserIdSet().add(userId);
        likes.putIfAbsent(filmId, filmLike);
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
        likes.get(filmId).getUserIdSet().remove(userId);
    }

    @Override
    public Collection<Like> getLikes() {
        return likes.values();
    }
}
