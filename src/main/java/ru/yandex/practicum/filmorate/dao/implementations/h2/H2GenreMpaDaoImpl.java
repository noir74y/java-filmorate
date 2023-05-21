package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@Component("H2GenreMpaDaoImpl")
@Slf4j
public class H2GenreMpaDaoImpl implements GenreMpaDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        SqlRowSet row = getRow("genre", genreId);
        row.next();
        return new Genre(row.getInt("id"), row.getString("name"));
    }

    @Override
    public Mpa getMpa(Integer mpaId) {
        SqlRowSet row = getRow("mpa", mpaId);
        row.next();
        return new Mpa(row.getInt("id"), row.getString("name"));
    }

    @Override
    public boolean isGenreExists(Integer genreId) {
        SqlRowSet row = getRow("genre", genreId);
        return row.next();
    }

    @Override
    public boolean isMpaExists(Integer mpaId) {
        SqlRowSet row = getRow("mpa", mpaId);
        return row.next();
    }

    private SqlRowSet getRow(String table, Integer id) {
        return jdbcTemplate.queryForRowSet("SELECT * FROM " + table + " WHERE id = ?", id);
    }
}
