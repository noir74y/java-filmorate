package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.model.*;

import java.time.Duration;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Component("H2FilmDaoImpl")
@Slf4j
public class H2FilmDaoImpl extends H2GenericImpl implements FilmDao {
    @Autowired
    H2GenreMpaDaoImpl h2GenreMpaDao;

    @Override
    public Collection<Film> list() {
        return jdbcTemplate.query("SELECT * FROM films ORDER BY id",
                (resultSet, rowNum) -> new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        Objects.requireNonNull(resultSet.getDate("releaseDate")).toLocalDate(),
                        Duration.ofMinutes(resultSet.getInt("duration")),
                        h2GenreMpaDao.getMpa(resultSet.getInt("mpa_id")).orElse(null),
                        h2GenreMpaDao.listFilmGenres(resultSet.getInt("id"))));
    }

    @Override
    public Optional<Film> get(Integer filmId) {
        SqlRowSet sqlRowSet = getRowById("films", filmId).orElse(null);
        return sqlRowSet != null ? Optional.of(new Film(
                sqlRowSet.getInt("id"),
                sqlRowSet.getString("name"),
                sqlRowSet.getString("description"),
                Objects.requireNonNull(sqlRowSet.getDate("releaseDate")).toLocalDate(),
                Duration.ofMinutes(sqlRowSet.getInt("duration")),
                h2GenreMpaDao.getMpa(sqlRowSet.getInt("mpa_id")).orElse(null),
                h2GenreMpaDao.listFilmGenres(sqlRowSet.getInt("id")))
        ) : Optional.empty();
    }

    @Override
    public Film create(Film film) {
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return getRowById("films", filmId).isPresent();
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {

    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {

    }

    @Override
    public Collection<FilmLikes> listFilmsLikes() {
        return null;
    }

    @Override
    public FilmLikes getFilmLikes(Integer filmId) {
        return null;
    }

    @Override
    public void clear() {

    }
}
