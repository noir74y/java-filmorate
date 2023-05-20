package ru.yandex.practicum.filmorate.dao.implementations.InMemory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.Generic.FilmDaoGeneric;

@Component("InMemoryFilmDaoImpl")
@Primary
public class FilmDaoInMemory extends FilmDaoGeneric {
}
