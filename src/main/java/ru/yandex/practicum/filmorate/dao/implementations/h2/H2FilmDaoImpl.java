package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.Collection;

@Component("H2FilmDaoImpl")
@Slf4j
public class H2FilmDaoImpl extends H2GenericImpl implements FilmDao {
    @Override
    public Collection<Film> list() {
        return null;
    }

    @Override
    public Film get(Integer filmId) {
        return null;
    }

    @Override
    public Film create(Film film) {
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return true;
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {

    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {

    }

    @Override
    public Collection<FilmLikes> listFilmsLikes() {
        return null;
    }

    @Override
    public FilmLikes getRate(Integer filmId) {
        return null;
    }

    @Override
    public void clear() {

    }
}
