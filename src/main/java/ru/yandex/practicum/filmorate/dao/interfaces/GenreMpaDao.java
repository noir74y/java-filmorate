package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;
import java.util.Optional;

public interface GenreMpaDao {
    Collection<Genre> listGenre();

    Collection<Mpa> listMpa();

    Optional<Genre> getGenre(Integer genreId);

    Optional<Mpa> getMpa(Integer mpaId);
}
