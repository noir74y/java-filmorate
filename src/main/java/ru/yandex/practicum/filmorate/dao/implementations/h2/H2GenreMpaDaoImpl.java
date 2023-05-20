package ru.yandex.practicum.filmorate.dao.implementations.h2;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.*;

import java.util.Collection;

@Component("H2GenreMpaDaoImpl")
public class H2GenreMpaDaoImpl implements GenreMpaDao {
    @Override
    public Collection<Genre> listGenre() {
        return null;
    }

    @Override
    public Genre getGenre(Integer genreId) {
        return null;
    }

    @Override
    public Collection<Mpa> listMpa() {
        return null;
    }

    @Override
    public Mpa getMpa(Integer mpaId) {
        return null;
    }
}
