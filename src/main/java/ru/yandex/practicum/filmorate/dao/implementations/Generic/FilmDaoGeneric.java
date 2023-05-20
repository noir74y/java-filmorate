package ru.yandex.practicum.filmorate.dao.implementations.Generic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.filmorate.dao.implementations.InMemory.StorageInMemory;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Like;

import java.util.Collection;
import java.util.HashSet;

@Slf4j
@RequiredArgsConstructor
public abstract class FilmDaoGeneric implements FilmDao {
    @Autowired
    protected StorageInMemory inMemory;

    @Autowired
    protected UserDao userDao;

    @Override
    public Collection<Film> list() {
        log.info("get films response {}", inMemory);
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
    public Film create(Film film) {
        log.info("film create request {}", film);
        film.setId();
        inMemory.createFilm(film.getId(), film);
        inMemory.createLike(film.getId(), new Like(film.getId(), new HashSet<>()));
        log.info("film create response {}", film);
        return film;
    }

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

    @Override
    public void addLike(Integer filmId, Integer userId) {
        if (isFilmExists(filmId) && userDao.isUserExists(userId)) {
            inMemory.getLike(filmId).getLikedUsersId().add(userId);
        } else processNotFoundException(filmId, userId);
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
        if (isFilmExists(filmId) && userDao.isUserExists(userId))
            inMemory.getLike(filmId).getLikedUsersId().remove(userId);
        else processNotFoundException(filmId, userId);
    }

    public Collection<Like> getLikes() {
        return inMemory.getLikes();
    }

    public Like getRate(Integer filmId) {
        return inMemory.getLike(filmId);
    }

    @Override
    public void clear() {
        inMemory.clearFilms();
        inMemory.clearLikes();
        userDao.clear();
    }

    private void processNotFoundException(Integer filmId, Integer userId) {
        if (!isFilmExists(filmId)) {
            log.error("no such filmId {}", filmId);
            throw new NotFoundException("no such filmId", String.valueOf(filmId));
        } else if (!userDao.isUserExists(userId)) {
            log.error("no such userId {}", userId);
            throw new NotFoundException("no such userId", String.valueOf(userId));
        }
    }
}
