package ru.yandex.practicum.filmorate.dao.interfaces.h2;

import ru.yandex.practicum.filmorate.dao.interfaces.generic.GenericStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface H2Storage extends GenericStorage {
    void createFilm(Film film);
    void createUser(User user);
    Collection<Mpa> getMpa();
    Mpa getMpa(Integer mpaId);
    Collection<Genre> getGenre();
    Mpa getGenre(Integer genreId);
}
