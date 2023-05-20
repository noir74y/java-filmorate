package ru.yandex.practicum.filmorate.dao.implementations.h2;

import ru.yandex.practicum.filmorate.dao.interfaces.H2Storage;
import ru.yandex.practicum.filmorate.model.*;

import java.util.Collection;
import java.util.Set;

public class H2StorageImpl implements H2Storage {
    @Override
    public Collection<Film> getFilms() {
        return null;
    }

    @Override
    public Film getFilm(Integer filmId) {
        return null;
    }

    @Override
    public void createFilmLikes(Integer filmId, FilmLikes filmLikes) {
    }

    @Override
    public void updateFilm(Integer filmId, Film film) {
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return false;
    }

    @Override
    public Collection<FilmLikes> getLikes() {
        return null;
    }

    @Override
    public FilmLikes getFilmLikes(Integer filmId) {
        return null;
    }

    @Override
    public void clearFilms() {

    }

    @Override
    public void clearLikes() {

    }

    @Override
    public Collection<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(Integer userId) {
        return null;
    }

    @Override
    public void createUser(Integer userId, User user) {

    }

    @Override
    public void createFriends(Integer userId, Set<Integer> friends) {

    }

    @Override
    public void updateUser(Integer userId, User user) {

    }

    @Override
    public boolean isUserExists(Integer userId) {
        return false;
    }

    @Override
    public Set<Integer> getFriends(Integer userId) {
        return null;
    }

    @Override
    public void clearUsers() {

    }

    @Override
    public void clearFriends() {

    }

    @Override
    public Film createFilm(Film film) {
        return null;
    }

    @Override
    public void createUser(User user) {
    }

    @Override
    public Collection<Mpa> getMpa() {
        return null;
    }

    @Override
    public Mpa getMpa(Integer mpaId) {
        return null;
    }

    @Override
    public Collection<Genre> getGenre() {
        return null;
    }

    @Override
    public Mpa getGenre(Integer genreId) {
        return null;
    }
}
