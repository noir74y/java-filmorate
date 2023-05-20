package ru.yandex.practicum.filmorate.dao.implementations.InMemory;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.Storage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Like;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component("StorageInMemory")
public class StorageInMemory implements Storage {
    private final Map<Integer, Film> films = new HashMap<>();
    private final Map<Integer, Like> likes = new HashMap<>();

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
}
