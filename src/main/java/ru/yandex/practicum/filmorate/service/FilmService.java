package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmService {
    @Autowired
    private FilmDao filmDao;

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
        return filmDao.getLikes()
                .stream()
                .sorted()
                .limit(count)
                .map(FilmLikes::getFilmId)
                .map(filmDao::get)
                .collect(Collectors.toList());
    }
}
