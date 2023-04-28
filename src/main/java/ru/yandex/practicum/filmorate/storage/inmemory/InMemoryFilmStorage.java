package ru.yandex.practicum.filmorate.storage.inmemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private final Map<Integer, Set<Integer>> likes = new HashMap<>();

    @Override
    public Collection<Film> list() {
        log.info("get films response {}", films);
        return films.values();
    }

    @Override
    public Film get(Integer filmId) {
        return films.get(filmId);
    }

    @Override
    public Film create(Film film) {
        log.info("film create request {}", film);
        film.setId();
        films.put(film.getId(), film);
        log.info("film create response {}", film);
        return film;
    }

    @Override
    public Film update(Film film) {
        log.info("film update request {}", film);
        if (films.containsKey(film.getId())) {
            films.replace(film.getId(), film);
            log.info("film update response {}", film);
            return film;
        }
        log.error("no such film {}", film);
        throw new NotFoundException("no such user");
    }


    @Override
    public void addLike(Integer filmId, Integer userId) {
        Set<Integer> likesList = likes.getOrDefault(filmId, new HashSet<>());
        likesList.add(userId);
        likes.putIfAbsent(filmId, likesList);
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
        likes.get(filmId).remove(userId);
    }

    @Override
    public Set<Integer> getLikes(Integer filmId) {
        return likes.get(filmId);
    }
}
