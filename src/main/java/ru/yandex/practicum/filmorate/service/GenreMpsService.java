package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@Service("GenreMpsService")
@Slf4j
public class GenreMpsService {
    @Autowired
    private GenreMpaDao genreMpaDao;

    public Collection<Genre> listGenre() {
        return genreMpaDao.listGenre();
    }

    public Genre getGenre(Integer genreId) {
        return genreMpaDao.getGenre(genreId);
    }

    public Collection<Mpa> listMpa() {
        return genreMpaDao.listMpa();
    }

    public Mpa getMpa(Integer mpaId) {
        return genreMpaDao.getMpa(mpaId);
    }

}
