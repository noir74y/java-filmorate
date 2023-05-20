package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

public interface H2Storage extends Storage {
    Collection<Mpa> getMpa();
    Mpa getMpa(Integer mpaId);
    Collection<Genre> getGenre();
    Mpa getGenre(Integer genreId);
}
