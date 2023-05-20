package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Like;

import java.util.Collection;

public interface Storage {
    Collection<Film> getFilms();

    Film get(Integer filmId);

    void createFilm(Film film);

    void createFilm(Integer filmId, Film film);

    void createLike(Integer filmId, Like like);

    void updateFilm(Integer filmId, Film film);

    boolean isFilmExists(Integer filmId);

    Collection<Like> getLikes();

    Like getLike(Integer filmId);

    void clearFilms();

    void clearLikes();
}

