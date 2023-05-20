package ru.yandex.practicum.filmorate.dao.interfaces.generic;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Set;

public interface GenericStorage {
    Collection<Film> getFilms();

    Film getFilm(Integer filmId);

    void createFilm(Integer filmId, Film film);

    void createLike(Integer filmId, FilmLikes filmLikes);

    void updateFilm(Integer filmId, Film film);

    boolean isFilmExists(Integer filmId);

    Collection<FilmLikes> getLikes();

    FilmLikes getFilmLikes(Integer filmId);

    void clearFilms();

    void clearLikes();

    Collection<User> getUsers();

    User getUser(Integer userId);

    void createUser(Integer userId, User user);

    void createFriends(Integer userId, Set<Integer> friends);

    void updateUser(Integer userId, User user);

    boolean isUserExists(Integer userId);

    Set<Integer> getFriends(Integer userId);

    void clearUsers();

    void clearFriends();

}

