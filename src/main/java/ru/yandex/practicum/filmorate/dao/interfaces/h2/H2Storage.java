package ru.yandex.practicum.filmorate.dao.interfaces.h2;

import ru.yandex.practicum.filmorate.dao.interfaces.generic.GenericStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

public interface H2Storage extends GenericStorage {
    void createFilm(Film film);
    void createUser(User user);
}
