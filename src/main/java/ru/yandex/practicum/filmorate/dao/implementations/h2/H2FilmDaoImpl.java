package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Component
@Slf4j
@Primary
@RequiredArgsConstructor
public class H2FilmDaoImpl extends H2GenericImpl implements FilmDao {
    private final H2GenreDaoImpl h2GenreDao;
    private final H2MpaDaoImpl h2MpaDao;

    @Override
    public Collection<Film> list() {
        Collection<Genre> allGenres = h2GenreDao.listGenre();

        String sql =
                "WITH film_genres AS ( " +
                        "SELECT " +
                        "film_id, LISTAGG(genre_id,',') WITHIN GROUP (ORDER BY film_id) AS genres_id_csv " +
                        "FROM GENRES GROUP BY film_id " +
                        ") " +
                        "SELECT " +
                        "films.id, films.name, films.description, films.release_date, films.duration, " +
                        "mpa.id AS mpa_id, mpa.name AS mpa_name, " +
                        "film_genres.genres_id_csv " +
                        "FROM films " +
                        "LEFT OUTER JOIN film_genres ON film_genres.film_id = films.id " +
                        "LEFT OUTER JOIN mpa ON mpa.id = films.mpa_id " +
                        "ORDER BY films.id";

        return jdbcTemplate.query(sql,
                (resultSet, rowNum) ->
                        Film.builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .description(resultSet.getString("description"))
                                .releaseDate(Objects.requireNonNull(resultSet.getDate("release_date")).toLocalDate())
                                .duration(Duration.ofSeconds(resultSet.getLong("duration")))
                                .mpa(Mpa.builder()
                                        .id(resultSet.getInt("mpa_id"))
                                        .name(resultSet.getString("mpa_name"))
                                        .build())
                                .genres(getFilmGenresSet(resultSet.getString("genres_id_csv"), allGenres))
                                .build());
    }

    @Override
    public Optional<Film> get(Integer filmId) {
        SqlRowSet sqlRowSet = getRowById("films", filmId).orElse(null);

        return sqlRowSet != null ? Optional.of(
                Film.builder()
                        .id(sqlRowSet.getInt("id"))
                        .name(sqlRowSet.getString("name"))
                        .description(sqlRowSet.getString("description"))
                        .releaseDate(Objects.requireNonNull(sqlRowSet.getDate("release_date")).toLocalDate())
                        .duration(Duration.ofSeconds(sqlRowSet.getLong("duration")))
                        .mpa(h2MpaDao.getMpa(sqlRowSet.getInt("mpa_id")).orElse(null))
                        .genres(h2GenreDao.listFilmGenres(sqlRowSet.getInt("id")))
                        .build()
        ) : Optional.empty();
    }

    @Override
    public Film create(Film film) {
        String sql = "INSERT INTO films (name, description, release_date, duration, mpa_id) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"});
            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            stmt.setDate(3, Date.valueOf(film.getReleaseDate()));
            stmt.setLong(4, film.getDuration().toSeconds());
            stmt.setInt(5, film.getMpa().getId());
            return stmt;
        }, keyHolder);

        film.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        attachGenresToFilm(film);

        return get(film.getId()).orElse(null);
    }

    @Override
    public Film update(Film film) {
        jdbcTemplate.update("UPDATE films SET name = ?, description = ?, release_date = ?, duration = ?, mpa_id = ? WHERE id = ?",
                film.getName(),
                film.getDescription(),
                Date.valueOf(film.getReleaseDate()),
                film.getDuration().toSeconds(),
                film.getMpa().getId(),
                film.getId());
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
        Optional.ofNullable(film.getGenres())
                .ifPresent(genres -> genres.stream()
                        .map(Genre::getId)
                        .distinct()
                        .forEach(genreId ->
                                jdbcTemplate.update(
                                        "INSERT INTO genres (film_id, genre_id) VALUES (?, ?)",
                                        film.getId(),
                                        genreId)));
    }

    private Set<Genre> getFilmGenresSet(String filmGenresIdCsv, Collection<Genre> allGenres) {

        if (filmGenresIdCsv == null) return new HashSet<>();

        Set<Integer> filmGenresId =
                Arrays.stream(filmGenresIdCsv.split(","))
                        .map(Integer::parseInt).sorted()
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        return allGenres.stream()
                .filter(genre -> filmGenresId.stream().anyMatch(filmGenreId -> genre.getId().equals(filmGenreId)))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
