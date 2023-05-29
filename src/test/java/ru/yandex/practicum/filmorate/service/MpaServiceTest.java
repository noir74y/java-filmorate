package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MpaServiceTest extends GenericServiceTest {
    @Test
    void getListMpa() throws Exception {
        List<Mpa> list = objectMapper.readValue(mpaMock.listEntityJson("/mpa"), new TypeReference<>() {
        });
        assertEquals(5, list.size());
    }

    @Test
    void getMpa() throws Exception {
        Mpa mpa1 = Mpa.builder().id(1).name("G").build();
        Mpa mpa2 = mpaMock.getEntity("/mpa/1", Mpa.class);
        assertEquals(mpa1, mpa2);
    }
}
