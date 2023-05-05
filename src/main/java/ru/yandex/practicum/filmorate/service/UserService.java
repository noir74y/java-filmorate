package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.interfaces.UserStorage;

import java.util.*;
import java.util.stream.Collectors;

@Service("UserService")
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;

    public Collection<User> list() {
        return userStorage.list();
    }

    public User get(Integer userId) {
        return userStorage.get(userId);
    }

    public User create(User user) {
        return userStorage.create(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public void addFriendship(Integer userId1, Integer userId2) {
        userStorage.addFriend(userId1, userId2);
        userStorage.addFriend(userId2, userId1);
    }

    public void deleteFriendship(Integer userId1, Integer userId2) {
        userStorage.deleteFriend(userId1, userId2);
        userStorage.deleteFriend(userId2, userId1);
    }

    public Collection<User> getCommonFriends(Integer userId1, Integer userId2) {
        Set<Integer> commonFriends = new HashSet<>(userStorage.getFriends(userId1));
        commonFriends.retainAll(userStorage.getFriends(userId2));
        return commonFriends.stream().map(userStorage::get).collect(Collectors.toList());
    }

    public Collection<User> getFriends(Integer userId) {
        return userStorage.getFriends(userId).stream().map(userStorage::get).collect(Collectors.toList());
    }
}
