package ru.yandex.practicum.filmorate.dao.implementations.H2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.Generic.UserDaoGeneric;

@Component("H2UserDaoImpl")
@Slf4j
public class UserDaoH2 extends UserDaoGeneric {
}
