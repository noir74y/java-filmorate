package ru.yandex.practicum.filmorate.dao.implementations.InMemory;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.Generic.FilmDaoGeneric;
import ru.yandex.practicum.filmorate.dao.interfaces.FilmDao;

@Component("InMemoryFilmDaoImpl")
public class FilmDaoInMemory extends FilmDaoGeneric {
}
