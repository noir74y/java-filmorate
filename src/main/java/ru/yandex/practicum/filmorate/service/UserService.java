package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public void addFriendship(Integer userId1, Integer userId2) {
        userDao.addFriend(userId1, userId2);
        userDao.addFriend(userId2, userId1);
    }

    public void deleteFriendship(Integer userId1, Integer userId2) {
        userDao.deleteFriend(userId1, userId2);
        userDao.deleteFriend(userId2, userId1);
    }

    public Collection<User> getCommonFriends(Integer userId1, Integer userId2) {
        Set<Integer> commonFriends = new HashSet<>(userDao.getFriends(userId1));
        commonFriends.retainAll(userDao.getFriends(userId2));
        return commonFriends.stream().map(userDao::get).collect(Collectors.toList());
    }

    public Collection<User> getFriends(Integer userId) {
        return userDao.getFriends(userId).stream().map(userDao::get).collect(Collectors.toList());
    }
}
