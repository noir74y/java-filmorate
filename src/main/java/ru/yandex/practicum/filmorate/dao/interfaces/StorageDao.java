package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Set;

public interface StorageDao {
    Collection<Film> listFilms();

    Film getFilm(Integer filmId);

    Film createFilm(Film film);

    Film updateFilm(Film film);

    void createFilmLikes(Integer filmId, FilmLikes filmLikes);


    boolean isFilmExists(Integer filmId);

    Collection<FilmLikes> listFilmsLikes();

    FilmLikes listFilmLikes(Integer filmId);

    void clearFilms();

    void clearLikes();

    Collection<User> listUsers();

    User getUser(Integer userId);

    User createUser(User user);

    void createFriends(Integer userId, Set<Integer> friends);

    User updateUser(User user);

    boolean isUserExists(Integer userId);

    Set<Integer> listUserFriends(Integer userId);

    void clearUsers();

    void clearFriends();
}

