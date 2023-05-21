package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@Component("H2GenreMpaDaoImpl")
@Slf4j
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
