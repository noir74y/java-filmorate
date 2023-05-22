package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreMpaDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@Service("GenreMpsService")
@Slf4j
public class GenreMpaService {
    @Autowired
    private GenreMpaDao genreMpaDao;

    public Collection<Genre> listGenre() {
        Collection<Genre> listGenre = genreMpaDao.listGenre();
        log.info("get listGenre response {}", listGenre);
        return listGenre;
    }

    public Collection<Mpa> listMpa() {
        Collection<Mpa> listMpa = genreMpaDao.listMpa();
        log.info("get listMpa response {}", listMpa);
        return listMpa;
    }

    public Genre getGenre(Integer genreId) {
        return genreMpaDao.getGenre(genreId).orElseThrow(() -> {
            log.error("no such genreId {}", genreId);
            return new NotFoundException("no such genreId", String.valueOf(genreId));
        });
    }

    public Mpa getMpa(Integer mpaId) {
        return genreMpaDao.getMpa(mpaId).orElseThrow(() -> {
            log.error("no such genreId {}", mpaId);
            return new NotFoundException("no such mpaId", String.valueOf(mpaId));
        });
    }
}
