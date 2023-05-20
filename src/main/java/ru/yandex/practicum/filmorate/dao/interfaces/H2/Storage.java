package ru.yandex.practicum.filmorate.dao.interfaces.H2;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

public interface Storage extends ru.yandex.practicum.filmorate.dao.interfaces.Storage {
    void createFilm(Film film);
    void createUser(User user);
}
