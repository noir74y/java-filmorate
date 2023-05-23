package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;
import ru.yandex.practicum.filmorate.model.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component("H2FilmDaoImpl")
@Slf4j
@Primary
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
        String sql = "INSERT INTO films (name, description, release_date, duration, map_id) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"});
            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            stmt.setDate(3, Date.valueOf(film.getReleaseDate()));
            stmt.setLong(4, film.getDuration().toMinutes());
            stmt.setInt(5, film.getMpa().getId());
            return stmt;
        }, keyHolder);

        film.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        attachGenresToFilm(film);

        return get(film.getId()).orElse(null);
    }

    @Override
    public Film update(Film film) {
        jdbcTemplate.update("UPDATE films SET name = ?, description = ?, release_date = ?, duration = ?, map_id = ? WHERE id = ?",
                film.getName(), film.getDescription(), Date.valueOf(film.getReleaseDate()), film.getDuration().toMinutes(), film.getMpa().getId(), film.getId());
        attachGenresToFilm(film);
        return get(film.getId()).orElse(null);
    }

    @Override
    public boolean isFilmExists(Integer filmId) {
        return getRowById("films", filmId).isPresent();
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {
        jdbcTemplate.update("INSERT INTO likes (film_id, user_id) VALUES (?, ?)", filmId, userId);
    }

    @Override
    public void deleteLike(Integer filmId, Integer userId) {
        jdbcTemplate.update("DELETE FROM likes WHERE film_id = ? AND user_id = ?", filmId, userId);
    }

    @Override
    public Collection<FilmLikes> listFilmsLikes() {
        return new HashSet<>(jdbcTemplate.queryForList("SELECT id FROM films", Integer.class))
                .stream()
                .map(this::getFilmLikes).collect(Collectors.toList());
    }

    @Override
    public FilmLikes getFilmLikes(Integer filmId) {
        return new FilmLikes(filmId,
                new HashSet<>(jdbcTemplate.queryForList("SELECT user_id FROM likes WHERE film_id = ?", Integer.class, filmId)));
    }

    @Override
    public void clear() {
        jdbcTemplate.update("DELETE FROM likes");
        jdbcTemplate.update("DELETE FROM genres");
        jdbcTemplate.update("DELETE FROM films");
        jdbcTemplate.update("ALTER TABLE films ALTER COLUMN id RESTART WITH 1");
    }

    private void attachGenresToFilm(Film film) {
        jdbcTemplate.update("DELETE FROM genres WHERE film_id = ?", film.getId());
        film.getGenres().forEach(genre -> jdbcTemplate.update("INSERT INTO genres (film_id, genre_id) VALUES (?, ?)", film.getId(), genre.getId()));
    }
}
