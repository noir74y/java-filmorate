package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.interfaces.UserStorage;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;

    public void addFriendship(Integer userHost, Integer userGuest) {
        userStorage.addFriend(userHost, userGuest);
        userStorage.addFriend(userGuest, userHost);
    }

    public void deleteFriendship(Integer userHost, Integer userGuest) {
        userStorage.deleteFriend(userHost, userGuest);
        userStorage.deleteFriend(userGuest, userHost);
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
