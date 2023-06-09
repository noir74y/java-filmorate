package ru.yandex.practicum.filmorate.dao.implementations.inMemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.*;

@Component("InMemoryFilmDaoImpl")
@Slf4j
public class InMemoryFilmDaoImpl implements FilmDao {
    private final Map<Integer, Film> films = new HashMap<>();
    private final Map<Integer, FilmLikes> likes = new HashMap<>();

    @Override
    public Collection<Film> list() {
        return films.values();
    }

    @Override
    public Optional<Film> get(Integer filmId) {
        Film film = films.get(filmId);
        return film != null ? Optional.of(film) : Optional.empty();
    }

    @Override
    public Film create(Film film) {
        film.setId();
        films.put(film.getId(), film);
        likes.put(film.getId(), new FilmLikes(film.getId(), new HashSet<>()));
        return film;
    }

    @Override
    public Film update(Film film) {
        films.replace(film.getId(), film);
        return film;
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return films.containsKey(filmId);
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {
        likes.get(filmId).getLikedUsersId().add(userId);
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
        likes.get(filmId).getLikedUsersId().remove(userId);
    }

    @Override
    public Collection<FilmLikes> listFilmsLikes() {
        return likes.values();
    }

    @Override
    public FilmLikes getFilmLikes(Integer filmId) {
        return likes.get(filmId);
    }

    @Override
    public void clear() {
        films.clear();
        likes.clear();
    }
}
