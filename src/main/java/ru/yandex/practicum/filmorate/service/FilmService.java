package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;

import java.util.*;
import java.util.stream.Collectors;

@Service("FilmService")
@Slf4j
public class FilmService {
    @Autowired
    private FilmDao genericFilmDao;

    public Collection<Film> list() {
        return genericFilmDao.list();
    }

    public Film get(Integer filmId) {
        return genericFilmDao.get(filmId);
    }

    public Film create(Film film) {
        return genericFilmDao.create(film);
    }

    public Film update(Film film) {
        return genericFilmDao.update(film);
    }

    public void addLike(Integer filmId, Integer userId) {
        genericFilmDao.addLike(filmId, userId);
    }

    public void deleteLike(Integer filmId, Integer userId) {
        genericFilmDao.deleteLike(filmId, userId);
    }

    public Collection<Film> getPopular(Integer count) {
        return genericFilmDao.listFilmsLikes()
                .stream()
                .sorted()
                .limit(count)
                .map(FilmLikes::getFilmId)
                .map(genericFilmDao::get)
                .collect(Collectors.toList());
    }
}
