package ru.yandex.practicum.filmorate.dao.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.filmorate.dao.interfaces.StorageDao;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;


@Slf4j
public abstract class UserDaoImpl implements UserDao {
    @Autowired
    protected StorageDao storageDao;

    @Override
    public abstract User create(User user);
    @Override
    public abstract void addFriendship(Integer userId1, Integer userId2);
    @Override
    public abstract void deleteFriendship(Integer userId1, Integer userId2);

    @Override
    public Collection<User> list() {
        log.info("get users response {}", storageDao.listUsers());
        return storageDao.listUsers();
    }

    @Override
    public User get(Integer userId) {
        if (isUserExists(userId))
            return storageDao.getUser(userId);

        log.error("no such userId {}", userId);
        throw new NotFoundException("no such userId", String.valueOf(userId));
    }

    @Override
    public User update(User user) {
        log.info("user update request {}", user);
        if (isUserExists(user.getId())) {
            setUserName(user);
            storageDao.updateUser(user);
            log.info("user update response {}", user);
            return user;
        }
        log.error("no such user {}", user);
        throw new NotFoundException("no such userId", String.valueOf(user.getId()));
    }

    @Override
    public boolean isUserExists(Integer userId) {
        return storageDao.isUserExists(userId);
    }

    @Override
    public abstract void addFriend(Integer userId, Integer friendId);

    @Override
    public abstract void deleteFriend(Integer userId, Integer friendId);

    @Override
    public Set<Integer> listUserFriends(Integer userId) {
        return Optional.ofNullable(storageDao.listUserFriends(userId)).orElse(new HashSet<>());
    }

    @Override
    public void clear() {
        storageDao.clearUsers();
        storageDao.clearFriends();
    }

    protected void setUserName(User user) {
        user.setName(user.getName() == null || user.getName().isBlank() ? user.getLogin() : user.getName());
    }

    protected void processNotFoundException(Integer userHostId, Integer userFriendId) {
        if (!isUserExists(userHostId)) {
            log.error("no such userHostId {}", userHostId);
            throw new NotFoundException("no such userHostId", String.valueOf(userHostId));
        } else if (!isUserExists(userFriendId)) {
            log.error("no such userFriendId {}", userFriendId);
            throw new NotFoundException("no such userFriendId", String.valueOf(userFriendId));
        }
    }
}
