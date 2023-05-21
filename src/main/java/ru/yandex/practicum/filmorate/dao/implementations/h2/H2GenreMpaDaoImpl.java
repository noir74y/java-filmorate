package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Genre getGenre(Integer genreId) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("SELECT * FROM genre WHERE id = ?", genreId);
        if (userRows.next())
           return new Genre(userRows.getInt("id"),userRows.getString("name"));
        return null;
    }

    @Override
    public boolean isGenreExists(Integer genreId) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("SELECT * FROM genre WHERE id = ?", genreId);
        if (userRows.next())
            return true;
        return false;
    }

    @Override
    public Collection<Mpa> listMpa() {
        return jdbcTemplate.query("SELECT * FROM mpa ORDER BY id", (rs, rowNum) -> new Mpa(rs.getInt("id"), rs.getString("name")));
    }

    @Override
    public Mpa getMpa(Integer mpaId) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("SELECT * FROM mpa WHERE id = ?", mpaId);
        if (userRows.next())
            return new Mpa(userRows.getInt("id"),userRows.getString("name"));
        return null;
    }

    @Override
    public boolean isMpaExists(Integer mpaId) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("SELECT * FROM mpa WHERE id = ?", mpaId);
        if (userRows.next())
            return true;
        return false;
    }

    private Genre makeGenre(ResultSet rs) throws SQLException {
        return new Genre(rs.getInt("id"), rs.getString("name"));
    }

    private Mpa makeMpa(ResultSet rs) throws SQLException {
        return new Mpa(rs.getInt("id"), rs.getString("name"));
    }

}
