package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;
import java.util.Optional;

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
    public Optional<Genre> getGenre(Integer id) {
        SqlRowSet row = getRowById("genre", id).orElse(null);
        return row != null ? Optional.of(new Genre(row.getInt("id"), row.getString("name"))) : Optional.empty();
    }

    @Override
    public Optional<Mpa> getMpa(Integer id) {
        SqlRowSet row = getRowById("mpa", id).orElse(null);
        return row != null ? Optional.of(new Mpa(row.getInt("id"), row.getString("name"))) : Optional.empty();
    }
}
