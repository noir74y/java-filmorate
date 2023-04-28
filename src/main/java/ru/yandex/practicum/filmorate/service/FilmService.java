package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Rate;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {
    private final FilmStorage filmStorage;

    void addLike(Integer filmId, Integer userId) {
        filmStorage.addLike(filmId, userId);
    }

    void deleteLike(Integer filmId, Integer userId) {
        filmStorage.deleteLike(filmId, userId);
    }

    Collection<Film> getPopular(int count) {
        return new TreeSet<>(filmStorage
                .getRates())
                .stream()
                .limit(count)
                .map(Rate::getFilmId)
                .map(filmStorage::get)
                .collect(Collectors.toList());
    }
}
