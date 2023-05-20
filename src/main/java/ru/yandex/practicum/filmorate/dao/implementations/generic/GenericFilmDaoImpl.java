package ru.yandex.practicum.filmorate.dao.implementations.generic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.filmorate.dao.interfaces.generic.GenericFilmDao;
import ru.yandex.practicum.filmorate.dao.interfaces.generic.GenericStorage;
import ru.yandex.practicum.filmorate.dao.interfaces.generic.GenericUserDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public abstract class GenericFilmDaoImpl implements GenericFilmDao {
    @Autowired
    protected GenericStorage genericStorage;
    @Autowired
    protected GenericUserDao genericUserDao;

    @Override
    public abstract Film create(Film film);
    @Override
    public abstract void addLike(Integer filmId, Integer userId);
    @Override
    public abstract void deleteLike(Integer filmId, Integer userId);

    @Override
    public Collection<Film> list() {
        log.info("get films response {}", genericStorage.getFilms());
        return genericStorage.getFilms();
    }

    @Override
    public Film get(Integer filmId) {
        if (isFilmExists(filmId))
            return genericStorage.getFilm(filmId);

        log.error("no such filmId {}", filmId);
        throw new NotFoundException("no such filmId", filmId.toString());
    }

    @Override
    public Film update(Film film) {
        log.info("film update request {}", film);
        if (isFilmExists(film.getId())) {
            genericStorage.updateFilm(film.getId(), film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}", film);
        throw new NotFoundException("no such filmId", String.valueOf(film.getId()));
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return genericStorage.isFilmExists(filmId);
    }

    public Collection<FilmLikes> getLikes() {
        return genericStorage.getLikes();
    }

    public FilmLikes getRate(Integer filmId) {
        return genericStorage.getFilmLikes(filmId);
    }

    @Override
    public void clear() {
        genericStorage.clearFilms();
        genericStorage.clearLikes();
        genericUserDao.clear();
    }

    protected void processNotFoundException(Integer filmId, Integer userId) {
        if (!isFilmExists(filmId)) {
            log.error("no such filmId {}", filmId);
            throw new NotFoundException("no such filmId", String.valueOf(filmId));
        } else if (!genericUserDao.isUserExists(userId)) {
            log.error("no such userId {}", userId);
            throw new NotFoundException("no such userId", String.valueOf(userId));
        }
    }
}
