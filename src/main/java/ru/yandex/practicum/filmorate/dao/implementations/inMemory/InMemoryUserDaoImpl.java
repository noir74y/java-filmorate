package ru.yandex.practicum.filmorate.dao.implementations.inMemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.generic.UserDaoImpl;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashSet;

@Component("InMemoryUserDaoImpl")
@Primary
@Slf4j
public class InMemoryUserDaoImpl extends UserDaoImpl {
    @Override
    public User create(User user) {
        log.info("user create request {}", user);
        user.setId();
        setUserName(user);
        storageDao.createUser(user);
        storageDao.createFriends(user.getId(), new HashSet<>());
        log.info("user create response {}", user);
        return user;
    }

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

    @Override
    public void addFriend(Integer userId, Integer friendId) {
        if (isUserExists(userId) && isUserExists(friendId)) {
            storageDao.listUserFriends(userId).add(friendId);
        } else processNotFoundException(userId, friendId);
    }

    @Override
    public void deleteFriend(Integer userId, Integer friendId) {
        if (isUserExists(userId) && isUserExists(friendId))
            storageDao.listUserFriends(userId).remove(friendId);
        else processNotFoundException(userId, friendId);
    }
}
