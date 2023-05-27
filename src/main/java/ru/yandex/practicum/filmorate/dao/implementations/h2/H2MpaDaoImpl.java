package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.MpaDao;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;
import java.util.Optional;

@Component("H2MpaDaoImpl")
@Slf4j
public class H2MpaDaoImpl extends H2GenericImpl implements MpaDao {
    @Override
    public Collection<Mpa> listMpa() {
        return jdbcTemplate.query("SELECT * FROM mpa ORDER BY id", (rs, rowNum) -> new Mpa(rs.getInt("id"), rs.getString("name")));
    }

    @Override
    public Optional<Mpa> getMpa(Integer id) {
        SqlRowSet row = getRowById("mpa", id).orElse(null);
        return row != null ? Optional.of(new Mpa(row.getInt("id"), row.getString("name"))) : Optional.empty();
    }
}
