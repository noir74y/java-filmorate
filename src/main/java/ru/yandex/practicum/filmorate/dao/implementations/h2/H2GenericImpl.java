package ru.yandex.practicum.filmorate.dao.implementations.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.Optional;

public class H2GenericImpl {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    protected Optional<SqlRowSet> getRowById(String table, Integer id) {
        SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT * FROM " + table + " WHERE id = ?", id);
        return row.next() ? Optional.of(row) : Optional.empty();
    }
}
