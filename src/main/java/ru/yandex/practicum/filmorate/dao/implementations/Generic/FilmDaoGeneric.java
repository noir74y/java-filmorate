package ru.yandex.practicum.filmorate.dao.implementations.Generic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.filmorate.dao.implementations.InMemory.StorageInMemory;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public abstract class FilmDaoGeneric implements FilmDao {
    @Autowired
    protected StorageInMemory inMemory;

    @Autowired
    protected UserDao userDao;

    @Override
    public abstract void addLike(Integer filmId, Integer userId);

    @Override
    public abstract void deleteLike(Integer filmId, Integer userId);

    @Override
    public Collection<Film> list() {
        log.info("get films response {}", inMemory.getFilms());
        return inMemory.getFilms();
    }

    @Override
    public Film get(Integer filmId) {
        if (isFilmExists(filmId))
            return inMemory.getFilm(filmId);

        log.error("no such filmId {}", filmId);
        throw new NotFoundException("no such filmId", filmId.toString());
    }

    @Override
    public abstract Film create(Film film);

    @Override
    public Film update(Film film) {
        log.info("film update request {}", film);
        if (isFilmExists(film.getId())) {
            inMemory.updateFilm(film.getId(), film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}", film);
        throw new NotFoundException("no such filmId", String.valueOf(film.getId()));
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return inMemory.isFilmExists(filmId);
    }

    public Collection<FilmLikes> getLikes() {
        return inMemory.getLikes();
    }

    public FilmLikes getRate(Integer filmId) {
        return inMemory.getFilmLikes(filmId);
    }

    @Override
    public void clear() {
        inMemory.clearFilms();
        inMemory.clearLikes();
        userDao.clear();
    }

    protected void processNotFoundException(Integer filmId, Integer userId) {
        if (!isFilmExists(filmId)) {
            log.error("no such filmId {}", filmId);
            throw new NotFoundException("no such filmId", String.valueOf(filmId));
        } else if (!userDao.isUserExists(userId)) {
            log.error("no such userId {}", userId);
            throw new NotFoundException("no such userId", String.valueOf(userId));
        }
    }
}
