package ru.yandex.practicum.filmorate.storage.interfaces;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Rate;

import java.util.Collection;

public interface FilmStorage {
    Collection<Film> list();

    Film get(Integer filmId);

    Film create(Film film);

    Film update(Film film);

    void addLike(Integer filmId, Integer userId);

    void deleteLike(Integer filmId, Integer userId);

    Collection<Rate> getRates();
}
