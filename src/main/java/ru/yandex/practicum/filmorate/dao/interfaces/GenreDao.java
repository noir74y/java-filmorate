package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface GenreDao {
    Collection<Genre> listGenre();

    Optional<Genre> getGenre(Integer genreId);

    Set<Genre> listFilmGenres(Integer filmId);
}
