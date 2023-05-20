package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;

import java.util.*;
import java.util.stream.Collectors;

@Service("UserService")
@Slf4j
public class UserService {
    @Autowired
    private UserDao userDao;

    public Collection<User> list() {
        return userDao.list();
    }

    public User get(Integer userId) {
        return userDao.get(userId);
    }

    public User create(User user) {
        return userDao.create(user);
    }

    public User update(User user) {
        return userDao.update(user);
    }

    public void addFriendship(Integer userId, Integer friendId) {
        userDao.addFriendship(userId, friendId);
    }

    public void deleteFriendship(Integer userId, Integer friendId) {
        userDao.deleteFriendship(userId, friendId);
    }

    public Collection<User> getCommonFriends(Integer userId1, Integer userId2) {
        Set<Integer> commonFriends = new HashSet<>(userDao.listUserFriends(userId1));
        commonFriends.retainAll(userDao.listUserFriends(userId2));
        return commonFriends.stream().map(userDao::get).collect(Collectors.toList());
    }

    public Collection<User> getFriends(Integer userId) {
        return userDao.listUserFriends(userId).stream().map(userDao::get).collect(Collectors.toList());
    }
}
