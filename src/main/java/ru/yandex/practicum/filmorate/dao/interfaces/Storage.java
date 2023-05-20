package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Like;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Set;

public interface Storage {
    Collection<Film> getFilms();

    Film getFilm(Integer filmId);

    void createFilm(Film film);

    void createFilm(Integer filmId, Film film);

    void createLike(Integer filmId, Like like);

    void updateFilm(Integer filmId, Film film);

    boolean isFilmExists(Integer filmId);

    Collection<Like> getLikes();

    Like getLike(Integer filmId);

    void clearFilms();

    void clearLikes();

    Collection<User> getUsers();

    User getUser(Integer userId);

    void createUser(User user);

    void createUser(Integer userId, User user);

    void createFriends(Integer userId, Set<Integer> friends);

    void updateUser(Integer userId, User user);

    boolean isUserExists(Integer userId);

    Set<Integer> getFriends(Integer userId);

    void clearUsers();

    void clearFriends();

}

