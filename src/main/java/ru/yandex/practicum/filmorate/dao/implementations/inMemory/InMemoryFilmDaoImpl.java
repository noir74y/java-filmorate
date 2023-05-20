package ru.yandex.practicum.filmorate.dao.implementations.inMemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.FilmDaoImpl;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikes;

import java.util.HashSet;

@Component("InMemoryFilmDaoImpl")
@Slf4j
@Primary
public class InMemoryFilmDaoImpl extends FilmDaoImpl {

    @Override
    public Film create(Film film) {
        log.info("film create request {}", film);
        film.setId();
        storage.createFilm(film.getId(), film);
        storage.createFilmLikes(film.getId(), new FilmLikes(film.getId(), new HashSet<>()));
        log.info("film create response {}", film);
        return film;
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {
        if (isFilmExists(filmId) && userDao.isUserExists(userId)) {
            storage.getFilmLikes(filmId).getLikedUsersId().add(userId);
        } else processNotFoundException(filmId, userId);
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
        if (isFilmExists(filmId) && userDao.isUserExists(userId))
            storage.getFilmLikes(filmId).getLikedUsersId().remove(userId);
        else processNotFoundException(filmId, userId);
    }
}
