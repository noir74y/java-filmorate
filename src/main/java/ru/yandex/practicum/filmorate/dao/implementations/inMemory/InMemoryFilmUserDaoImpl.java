package ru.yandex.practicum.filmorate.dao.implementations.inMemory;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmUserDao;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component("StorageInMemory")
public class InMemoryFilmUserDaoImpl implements FilmUserDao {
    private final Map<Integer, Film> films = new HashMap<>();
    private final Map<Integer, FilmLikes> likes = new HashMap<>();
    protected final Map<Integer, User> users = new HashMap<>();
    protected final HashMap<Integer, Set<Integer>> friends = new HashMap<>();

    @Override
    public Collection<Film> listFilms() {
        return films.values();
    }

    @Override
    public Film getFilm(Integer filmId) {
        return films.get(filmId);
    }

    @Override
    public Film createFilm(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        films.replace(film.getId(), film);
        return film;
    }

    @Override
    public void createFilmLikes(Integer filmId, FilmLikes filmLikes) {
        likes.put(filmId, filmLikes);
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return films.containsKey(filmId);
    }

    @Override
    public Collection<FilmLikes> listFilmsLikes() {
        return likes.values();
    }

    @Override
    public FilmLikes listFilmLikes(Integer filmId) {
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
    public Collection<User> listUsers() {
        return users.values();
    }

    @Override
    public User getUser(Integer userId) {
        return users.get(userId);
    }

    @Override
    public User createUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        users.replace(user.getId(), user);
        return user;
    }

    @Override
    public void createFriends(Integer userId, Set<Integer> friends) {
        this.friends.put(userId, friends);
    }

    @Override
    public boolean isUserExists(Integer userId) {
        return users.containsKey(userId);
    }

    @Override
    public Set<Integer> listUserFriends(Integer userId) {
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
