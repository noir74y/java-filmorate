package ru.yandex.practicum.filmorate.dao.interfaces.generic;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.Collection;

public interface GenericFilmDao {
    Collection<Film> list();

    Film get(Integer filmId);

    Film create(Film film);

    Film update(Film film);

    boolean isFilmExists(Integer filmId);

    void addLike(Integer filmId, Integer userId);

    void deleteLike(Integer filmId, Integer userId);

    Collection<FilmLikes> getLikes();

    void clear();
}
