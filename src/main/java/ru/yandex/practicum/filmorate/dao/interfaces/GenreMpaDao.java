package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

public interface GenreMpaDao {
    Collection<Genre> listGenre();
    Genre getGenre(Integer genreId);
    Collection<Mpa> listMpa();
    Mpa getMpa(Integer mpaId);
}
