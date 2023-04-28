package ru.yandex.practicum.filmorate.storage.interfaces;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Set;

public interface UserStorage {
    Collection<User> list();

    User get(Integer userId);

    User create(User user);

    User update(User user);

    void addFriend(Integer userHost, Integer userGuest);

    void deleteFriend(Integer userHost, Integer userGuest);

    Set<Integer> getFriends(Integer userId);
}
