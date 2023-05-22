package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@Component("H2GenreMpaDaoImpl")
@Slf4j
public class H2GenreMpaDaoImpl extends H2GenericImpl implements GenreMpaDao {
    @Override
    public Collection<Genre> listGenre() {
        return jdbcTemplate.query("SELECT * FROM genre ORDER BY id", (rs, rowNum) -> new Genre(rs.getInt("id"), rs.getString("name")));
    }

    @Override
    public Collection<Mpa> listMpa() {
        return jdbcTemplate.query("SELECT * FROM mpa ORDER BY id", (rs, rowNum) -> new Mpa(rs.getInt("id"), rs.getString("name")));
    }

    @Override
    public Genre getGenre(Integer genreId) {
        SqlRowSet row = getRowById("genre", genreId);
        return new Genre(row.getInt("id"), row.getString("name"));
    }

    @Override
    public Mpa getMpa(Integer mpaId) {
        SqlRowSet row = getRowById("mpa", mpaId);
        return new Mpa(row.getInt("id"), row.getString("name"));
    }

    @Override
    public boolean isGenreExists(Integer genreId) {
        return isRowExists("genre", genreId);
    }

    @Override
    public boolean isMpaExists(Integer mpaId) {
        return isRowExists("mpa", mpaId);
    }
}
