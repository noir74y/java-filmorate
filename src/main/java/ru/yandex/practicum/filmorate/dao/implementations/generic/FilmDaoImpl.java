package ru.yandex.practicum.filmorate.dao.implementations.generic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmUserDao;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public abstract class FilmDaoImpl implements FilmDao {
    @Autowired
    protected FilmUserDao filmUserDao;
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
        log.info("get films response {}", filmUserDao.listFilms());
        return filmUserDao.listFilms();
    }

    @Override
    public Film get(Integer filmId) {
        if (isFilmExists(filmId))
            return filmUserDao.getFilm(filmId);

        log.error("no such filmId {}", filmId);
        throw new NotFoundException("no such filmId", filmId.toString());
    }

    @Override
    public Film update(Film film) {
        log.info("film update request {}", film);
        if (isFilmExists(film.getId())) {
            film = filmUserDao.updateFilm(film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}", film);
        throw new NotFoundException("no such filmId", String.valueOf(film.getId()));
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return filmUserDao.isFilmExists(filmId);
    }

    public Collection<FilmLikes> listFilmsLikes() {
        return filmUserDao.listFilmsLikes();
    }

    public FilmLikes getRate(Integer filmId) {
        return filmUserDao.listFilmLikes(filmId);
    }

    @Override
    public void clear() {
        filmUserDao.clearFilms();
        filmUserDao.clearLikes();
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
