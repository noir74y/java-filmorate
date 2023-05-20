package ru.yandex.practicum.filmorate.dao.implementations.InMemory;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.Storage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Like;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component("StorageInMemory")
public class StorageInMemory implements Storage {
    private final Map<Integer, Film> films = new HashMap<>();
    private final Map<Integer, Like> likes = new HashMap<>();
    protected final Map<Integer, User> users = new HashMap<>();
    protected final HashMap<Integer, Set<Integer>> friends = new HashMap<>();

    @Override
    public Collection<Film> getFilms() {
        return films.values();
    }

    @Override
    public Film getFilm(Integer filmId) {
        return films.get(filmId);
    }

    @Override
    public void createFilm(Film film) {
    }

    @Override
    public void createFilm(Integer filmId, Film film) {
        films.put(filmId, film);
    }

    @Override
    public void createLike(Integer filmId, Like like) {
        likes.put(filmId, like);
    }

    @Override
    public void updateFilm(Integer filmId, Film film) {
        films.replace(filmId, film);
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return films.containsKey(filmId);
    }

    @Override
    public Collection<Like> getLikes() {
        return likes.values();
    }

    @Override
    public Like getLike(Integer filmId) {
        return likes.get(filmId);
    }

    @Override
    public void clearFilms() {
        films.clear();
    }

    @Override
    public void clearLikes() {
        likes.clear();
    }

    @Override
    public Collection<User> getUsers() {
        return users.values();
    }

    @Override
    public User getUser(Integer userId) {
        return users.get(userId);
    }

    @Override
    public void createUser(User user) {
    }

    @Override
    public void createUser(Integer userId, User user) {
        users.put(userId, user);
    }

    @Override
    public void createFriends(Integer userId, Set<Integer> friends) {
        this.friends.put(userId, friends);
    }

    @Override
    public void updateUser(Integer userId, User user) {
        users.replace(userId, user);
    }

    @Override
    public boolean isUserExists(Integer userId) {
        return users.containsKey(userId);
    }

    @Override
    public Set<Integer> getFriends(Integer userId) {
        return friends.get(userId);
    }

    @Override
    public void clearUsers() {
        users.clear();
    }

    @Override
    public void clearFriends() {
        friends.clear();
    }
}
