package ru.yandex.practicum.filmorate.dao.implementations.InMemory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.Generic.UserDaoGeneric;

@Component("InMemoryUserDaoImpl")
@Primary
public class UserDaoInMemory extends UserDaoGeneric {


    @Override
    public void addFriendship(Integer userId1, Integer userId2) {
        addFriend(userId1, userId2);
        addFriend(userId2, userId1);
    }

    @Override
    public void deleteFriendship(Integer userId1, Integer userId2) {
        deleteFriend(userId1, userId2);
        deleteFriend(userId2, userId1);
    }
}
