package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.dao.interfaces.generic.GenericUserDao;

import java.util.*;
import java.util.stream.Collectors;

@Service("UserService")
@Slf4j
public class UserService {
    @Autowired
    private GenericUserDao genericUserDao;

    public Collection<User> list() {
        return genericUserDao.list();
    }

    public User get(Integer userId) {
        return genericUserDao.get(userId);
    }

    public User create(User user) {
        return genericUserDao.create(user);
    }

    public User update(User user) {
        return genericUserDao.update(user);
    }

    public void addFriendship(Integer userId, Integer friendId) {
        genericUserDao.addFriendship(userId, friendId);
    }

    public void deleteFriendship(Integer userId, Integer friendId) {
        genericUserDao.deleteFriendship(userId, friendId);
    }

    public Collection<User> getCommonFriends(Integer userId1, Integer userId2) {
        Set<Integer> commonFriends = new HashSet<>(genericUserDao.getFriends(userId1));
        commonFriends.retainAll(genericUserDao.getFriends(userId2));
        return commonFriends.stream().map(genericUserDao::get).collect(Collectors.toList());
    }

    public Collection<User> getFriends(Integer userId) {
        return genericUserDao.getFriends(userId).stream().map(genericUserDao::get).collect(Collectors.toList());
    }
}
