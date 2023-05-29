package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;
import java.util.Optional;

public interface MpaDao {
    Collection<Mpa> listMpa();

    Optional<Mpa> getMpa(Integer mpaId);
}
