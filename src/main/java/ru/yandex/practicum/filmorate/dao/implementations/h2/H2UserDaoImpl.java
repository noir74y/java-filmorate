package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.UserDaoImpl;
import ru.yandex.practicum.filmorate.model.User;

@Component("H2UserDaoImpl")
@Slf4j
public class H2UserDaoImpl extends UserDaoImpl {
    @Override
    public User create(User user) {
        return null;
    }

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
