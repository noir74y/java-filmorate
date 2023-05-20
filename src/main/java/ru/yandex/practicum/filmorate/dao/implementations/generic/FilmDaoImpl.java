package ru.yandex.practicum.filmorate.dao.implementations.generic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.dao.interfaces.StorageDao;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public abstract class FilmDaoImpl implements FilmDao {
    @Autowired
    protected StorageDao storageDao;
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
        log.info("get films response {}", storageDao.listFilms());
        return storageDao.listFilms();
    }

    @Override
    public Film get(Integer filmId) {
        if (isFilmExists(filmId))
            return storageDao.getFilm(filmId);

        log.error("no such filmId {}", filmId);
        throw new NotFoundException("no such filmId", filmId.toString());
    }

    @Override
    public Film update(Film film) {
        log.info("film update request {}", film);
        if (isFilmExists(film.getId())) {
            film = storageDao.updateFilm(film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}", film);
        throw new NotFoundException("no such filmId", String.valueOf(film.getId()));
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return storageDao.isFilmExists(filmId);
    }

    public Collection<FilmLikes> listFilmsLikes() {
        return storageDao.listFilmsLikes();
    }

    public FilmLikes getRate(Integer filmId) {
        return storageDao.listFilmLikes(filmId);
    }

    @Override
    public void clear() {
        storageDao.clearFilms();
        storageDao.clearLikes();
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
