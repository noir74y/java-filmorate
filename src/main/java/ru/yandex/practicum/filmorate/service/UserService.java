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

    public void addFriendship(Integer user1, Integer user2) {
        userStorage.addFriend(user1, user2);
        userStorage.addFriend(user2, user1);
    }

    public void deleteFriendship(Integer user1, Integer user2) {
        userStorage.deleteFriend(user1, user2);
        userStorage.deleteFriend(user2, user1);
    }
    
    public Collection<User> getCommonFriends(Integer user1, Integer user2) {
        Set<Integer> commonFriends = new HashSet<>(userStorage.getFriends(user1));
        commonFriends.retainAll(userStorage.getFriends(user2));
        return commonFriends.stream().map(userStorage::get).collect(Collectors.toList());
    }
}
