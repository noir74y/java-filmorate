package ru.yandex.practicum.filmorate.dao.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.dao.interfaces.Storage;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public abstract class FilmDaoImpl implements FilmDao {
    @Autowired
    protected Storage storage;
    @Autowired
    protected UserDao userDao;

    @Override
    public abstract Film create(Film film);
    @Override
    public abstract void addLike(Integer filmId, Integer userId);
    @Override
    public abstract void deleteLike(Integer filmId, Integer userId);

    @Override
    public Collection<Film> list() {
        log.info("get films response {}", storage.getFilms());
        return storage.getFilms();
    }

    @Override
    public Film get(Integer filmId) {
        if (isFilmExists(filmId))
            return storage.getFilm(filmId);

        log.error("no such filmId {}", filmId);
        throw new NotFoundException("no such filmId", filmId.toString());
    }

    @Override
    public Film update(Film film) {
        log.info("film update request {}", film);
        if (isFilmExists(film.getId())) {
            storage.updateFilm(film.getId(), film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}", film);
        throw new NotFoundException("no such filmId", String.valueOf(film.getId()));
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return storage.isFilmExists(filmId);
    }

    public Collection<FilmLikes> getLikes() {
        return storage.getLikes();
    }

    public FilmLikes getRate(Integer filmId) {
        return storage.getFilmLikes(filmId);
    }

    @Override
    public void clear() {
        storage.clearFilms();
        storage.clearLikes();
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
