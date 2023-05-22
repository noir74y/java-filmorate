package ru.yandex.practicum.filmorate.dao.implementations.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class H2GenericImpl {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    protected SqlRowSet getRowById(String table, Integer id) {
        SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT * FROM " + table + " WHERE id = ?", id);
        row.next();
        return row;
    }

    protected boolean isRowExists(String table, Integer rowId) {
        SqlRowSet row = getRowById("users", rowId);
        return row.next();
    }
}
