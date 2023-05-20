package ru.yandex.practicum.filmorate.dao.implementations.InMemory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.Generic.UserDaoGeneric;

@Component("InMemoryUserDaoImpl")
@Primary
public class UserDaoInMemory extends UserDaoGeneric {
}
