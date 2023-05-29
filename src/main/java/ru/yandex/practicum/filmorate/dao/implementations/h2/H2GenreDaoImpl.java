package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreDao;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Component("H2GenreDaoImpl")
@Slf4j
public class H2GenreDaoImpl extends H2GenericImpl implements GenreDao {
    @Override
    public Collection<Genre> listGenre() {
        return jdbcTemplate.query("SELECT * FROM genre ORDER BY id", (rs, rowNum) -> new Genre(rs.getInt("id"), rs.getString("name")));
    }

    @Override
    public Optional<Genre> getGenre(Integer id) {
        SqlRowSet row = getRowById("genre", id).orElse(null);
        return row != null ? Optional.of(new Genre(row.getInt("id"), row.getString("name"))) : Optional.empty();
    }

    @Override
    public Set<Genre> listFilmGenres(Integer filmId) {
        return new LinkedHashSet<>(jdbcTemplate.query("SELECT * FROM genre WHERE id in (SELECT genre_id FROM genres WHERE film_id = ?) ORDER BY id ASC",
                (rs, rowNum) -> new Genre(rs.getInt("id"), rs.getString("name")), filmId));
    }
}
