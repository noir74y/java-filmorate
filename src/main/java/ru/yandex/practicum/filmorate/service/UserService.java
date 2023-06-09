package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;

import java.util.*;
import java.util.stream.Collectors;

@Service("UserService")
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public Collection<User> list() {
        Collection<User> users = userDao.list();
        log.info("get users response {}", users);
        return users;
    }

    public User get(Integer userId) {
        return userDao.get(userId).orElseThrow(() -> {
            log.error("no such userId {}", userId);
            return new NotFoundException("no such userId", userId.toString());
        });
    }

    public User create(User user) {
        log.info("user create request {}", user);
        user = userDao.create(user);
        log.info("user create response {}", user);
        return user;
    }

    public User update(User user) {
        log.info("user update request {}", user);
        if (userDao.isUserExists(user.getId())) {
            user = userDao.update(user);
            log.info("user update response {}", user);
            return user;
        }
        log.error("no such user {}", user);
        throw new NotFoundException("no such userId", String.valueOf(user.getId()));
    }

    public void addFriend(Integer userId, Integer friendId) {
        if (userDao.isUserExists(userId) && userDao.isUserExists(friendId)) {
            userDao.addFriend(userId, friendId);
        } else processNotFoundException(userId, friendId);
    }

    public void deleteFriend(Integer userId, Integer friendId) {
        userDao.deleteFriend(userId, friendId);
    }

    public Collection<User> getCommonFriends(Integer userId1, Integer userId2) {
        Set<Integer> commonFriends = new HashSet<>(userDao.listUserFriends(userId1));
        commonFriends.retainAll(userDao.listUserFriends(userId2));
        return commonFriends.stream().map(id -> userDao.get(id).orElse(null)).collect(Collectors.toList());
    }

    public Collection<User> getFriends(Integer userId) {
        return userDao.listUserFriends(userId).stream().map(id -> userDao.get(id).orElse(null)).collect(Collectors.toList());
    }

    private void processNotFoundException(Integer userId, Integer friendId) {
        if (!userDao.isUserExists(userId)) {
            log.error("no such userId {}", userId);
            throw new NotFoundException("no such userId", String.valueOf(userId));
        } else if (!userDao.isUserExists(friendId)) {
            log.error("no such friendId {}", friendId);
            throw new NotFoundException("no such friendId", String.valueOf(friendId));
        }
    }
}
