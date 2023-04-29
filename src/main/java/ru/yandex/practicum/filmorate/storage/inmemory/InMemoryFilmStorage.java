package ru.yandex.practicum.filmorate.storage.inmemory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Rate;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.interfaces.UserStorage;

import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private final Map<Integer, Rate> rates = new HashMap<>();
    private final UserStorage userStorage;

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
    public boolean isFilmExists(Integer filmId) {
        return films.containsKey(filmId);
    }


    @Override
    public void addLike(Integer filmId, Integer userId) {
        if (isFilmExists(filmId) && userStorage.isUserExists(userId)) {
            Rate rate = rates.getOrDefault(filmId, new Rate(filmId, new HashSet<>()));
            rate.getLikedUsersId().add(userId);
            rates.putIfAbsent(filmId, rate);
        }
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
        if (isFilmExists(filmId) && userStorage.isUserExists(userId))
            rates.get(filmId).getLikedUsersId().remove(userId);
    }

    @Override
    public Collection<Rate> getRates() {
        return rates.values();
    }
}
