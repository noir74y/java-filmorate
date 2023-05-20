package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.FilmDaoImpl;
import ru.yandex.practicum.filmorate.model.Film;

@Component("H2FilmDaoImpl")
@Slf4j
public class H2FilmDaoImpl extends FilmDaoImpl {
    @Override
    public Film create(Film film) {
        return null;
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
    }
}
