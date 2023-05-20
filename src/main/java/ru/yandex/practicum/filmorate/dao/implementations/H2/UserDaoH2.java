package ru.yandex.practicum.filmorate.dao.implementations.H2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.Generic.UserDaoGeneric;

@Component("H2UserDaoImpl")
@Slf4j
public class UserDaoH2 extends UserDaoGeneric {
    @Override
    public void addFriendship(Integer userId1, Integer userId2) {
    }

    @Override
    public void deleteFriendship(Integer userId1, Integer userId2) {
    }

    @Override
    public void addFriend(Integer userId, Integer friendId) {
    }

    @Override
    public void deleteFriend(Integer userId, Integer friendId) {

    }
}
