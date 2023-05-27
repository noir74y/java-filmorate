package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.interfaces.MpaDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@Service("MpaService")
@Slf4j
public class MpaService {
    @Autowired
    private MpaDao mpaDao;

    public Collection<Mpa> listMpa() {
        Collection<Mpa> listMpa = mpaDao.listMpa();
        log.info("get listMpa response {}", listMpa);
        return listMpa;
    }

    public Mpa getMpa(Integer mpaId) {
        return mpaDao.getMpa(mpaId).orElseThrow(() -> {
            log.error("no such mpaId {}", mpaId);
            return new NotFoundException("no such mpaId", String.valueOf(mpaId));
        });
    }
}
