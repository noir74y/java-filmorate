package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;

import java.util.*;
import java.util.stream.Collectors;

@Service("FilmService")
@Slf4j
public class FilmService {
    @Autowired
    private FilmDao filmDao;

    @Autowired
    private UserDao userDao;

    public Collection<Film> list() {
        Collection<Film> films = filmDao.list();
        log.info("get films response {}", films);
        return films;
    }

    public Film get(Integer filmId) {
        return filmDao.get(filmId).orElseThrow(() -> {
            log.error("no such filmId {}", filmId);
            return new NotFoundException("no such filmId", filmId.toString());
        });
    }

    public Film create(Film film) {
        log.info("film create request {}", film);
        film = filmDao.create(film);
        log.info("film create response {}", film);
        return film;
    }

    public Film update(Film film) {
        log.info("film update request {}", film);
        if (filmDao.isFilmExists(film.getId())) {
            film = filmDao.update(film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}", film);
        throw new NotFoundException("no such filmId", String.valueOf(film.getId()));
    }

    public void addLike(Integer filmId, Integer userId) {
        if (filmDao.isFilmExists(filmId) && userDao.isUserExists(userId)) {
            filmDao.addLike(filmId, userId);
        } else processNotFoundException(filmId, userId);
    }

    public void deleteLike(Integer filmId, Integer userId) {
        if (filmDao.isFilmExists(filmId) && userDao.isUserExists(userId))
            filmDao.deleteLike(filmId, userId);
        else processNotFoundException(filmId, userId);
    }

    public Collection<Film> listPopular(Integer count) {
        return filmDao.listFilmsLikes()
                .stream()
                .sorted()
                .limit(count)
                .map(FilmLikes::getFilmId)
                .map(filmId -> filmDao.get(filmId).orElse(null))
                .collect(Collectors.toList());
    }

    private void processNotFoundException(Integer filmId, Integer userId) {
        if (!filmDao.isFilmExists(filmId)) {
            log.error("no such filmId {}", filmId);
            throw new NotFoundException("no such filmId", String.valueOf(filmId));
        } else if (!userDao.isUserExists(userId)) {
            log.error("no such userId {}", userId);
            throw new NotFoundException("no such userId", String.valueOf(userId));
        }
    }
}
