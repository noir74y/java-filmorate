package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.interfaces.GenreDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;

@Service("GenreService")
@Slf4j
public class GenreService {
    @Autowired
    private GenreDao genreDao;

    public Collection<Genre> listGenre() {
        Collection<Genre> listGenre = genreDao.listGenre();
        log.info("get listGenre response {}", listGenre);
        return listGenre;
    }

    public Genre getGenre(Integer genreId) {
        return genreDao.getGenre(genreId).orElseThrow(() -> {
            log.error("no such genreId {}", genreId);
            return new NotFoundException("no such genreId", String.valueOf(genreId));
        });
    }
}
