package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.*;
import java.util.stream.Collectors;

@Service("FilmService")
@Slf4j
public class FilmService {
    @Autowired
    private FilmDao filmDao;
    @Autowired
    private GenreMpaDao genreMpaDao;

    public Collection<Film> list() {
        return filmDao.list();
    }

    public Film get(Integer filmId) {
        return filmDao.get(filmId);
    }

    public Film create(Film film) {
        return filmDao.create(film);
    }

    public Film update(Film film) {
        return filmDao.update(film);
    }

    public void addLike(Integer filmId, Integer userId) {
        filmDao.addLike(filmId, userId);
    }

    public void deleteLike(Integer filmId, Integer userId) {
        filmDao.deleteLike(filmId, userId);
    }

    public Collection<Film> getPopular(Integer count) {
        return filmDao.listFilmsLikes()
                .stream()
                .sorted()
                .limit(count)
                .map(FilmLikes::getFilmId)
                .map(filmDao::get)
                .collect(Collectors.toList());
    }

    public Collection<Genre> listGenre() {
        return genreMpaDao.listGenre();
    }

    public Genre getGenre(Integer genreId) {
        return genreMpaDao.getGenre(genreId);
    }

    public Collection<Mpa> listMpa() {
        return genreMpaDao.listMpa();
    }

    public Mpa getMpa(Integer mpaId) {
        return genreMpaDao.getMpa(mpaId);
    }
}
