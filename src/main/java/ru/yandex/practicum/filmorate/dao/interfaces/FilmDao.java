package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.Collection;
import java.util.Optional;

public interface FilmDao {
    Collection<Film> list();

    Optional<Film> get(Integer filmId);

    Film create(Film film);

    Film update(Film film);

    boolean isFilmExists(Integer filmId);

    void addLike(Integer filmId, Integer userId);

    void deleteLike(Integer filmId, Integer userId);

    Collection<FilmLikes> listFilmsLikes();

    FilmLikes getFilmLikes(Integer filmId);

    void clear();
}
