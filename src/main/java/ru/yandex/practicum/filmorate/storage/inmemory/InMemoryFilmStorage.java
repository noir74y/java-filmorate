package ru.yandex.practicum.filmorate.storage.inmemory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Like;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.interfaces.UserStorage;

import java.util.*;

@Component("InMemoryFilmStorage")
@Slf4j
@RequiredArgsConstructor
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private final Map<Integer, Like> likes = new HashMap<>();

    private final UserStorage userStorage;

    @Override
    public Collection<Film> list() {
        log.info("get films response {}", films);
        return films.values();
    }

    @Override
    public Film get(Integer filmId) {
        if (isFilmExists(filmId))
            return films.get(filmId);

        log.error("no such filmId {}", filmId);
        throw new NotFoundException("no such filmId", String.valueOf(filmId.toString()));
    }

    @Override
    public Film create(Film film) {
        log.info("film create request {}", film);
        film.setId();
        films.put(film.getId(), film);
        likes.put(film.getId(), new Like(film.getId(), new HashSet<>()));
        log.info("film create response {}", film);
        return film;
    }

    @Override
    public Film update(Film film) {
        log.info("film update request {}", film);
        if (isFilmExists(film.getId())) {
            films.replace(film.getId(), film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}", film);
        throw new NotFoundException("no such filmId", String.valueOf(film.getId()));
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return films.containsKey(filmId);
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {
        if (isFilmExists(filmId) && userStorage.isUserExists(userId)) {
            likes.get(filmId).getLikedUsersId().add(userId);
        } else processNotFoundException(filmId, userId);
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
        if (isFilmExists(filmId) && userStorage.isUserExists(userId))
            likes.get(filmId).getLikedUsersId().remove(userId);
        else processNotFoundException(filmId, userId);
    }

    public Collection<Like> getLikes() {
        return likes.values();
    }

    public Like getRate(Integer filmId) {
        return likes.get(filmId);
    }

    @Override
    public void clear() {
        films.clear();
        likes.clear();
        userStorage.clear();
    }

    private void processNotFoundException(Integer filmId, Integer userId) {
        if (!isFilmExists(filmId)) {
            log.error("no such filmId {}", filmId);
            throw new NotFoundException("no such filmId", String.valueOf(filmId));
        } else if (!userStorage.isUserExists(userId)) {
            log.error("no such userId {}", userId);
            throw new NotFoundException("no such userId", String.valueOf(userId));
        }
    }
}
