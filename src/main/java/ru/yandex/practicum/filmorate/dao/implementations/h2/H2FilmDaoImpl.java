package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.generic.FilmDaoImpl;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@Component("H2FilmDaoImpl")
@Slf4j
public class H2FilmDaoImpl extends FilmDaoImpl implements FilmDao, GenreMpaDao {
    @Override
    public Film create(Film film) {
        return null;
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
    }

    @Override
    public Collection<Genre> listGenre() {
        return null;
    }

    @Override
    public Genre getGenre(Integer genreId) {
        return null;
    }

    @Override
    public Collection<Mpa> listMpa() {
        return null;
    }

    @Override
    public Mpa getMpa(Integer mpaId) {
        return null;
    }
}
