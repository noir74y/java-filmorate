package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Collection<User> list();

    Optional<User> get(Integer userId);

    User create(User user);

    User update(User user);

    boolean isUserExists(Integer userId);

    void addFriend(Integer userId, Integer friendId);

    void deleteFriend(Integer userId, Integer friendId);

    Set<Integer> listUserFriends(Integer userId);

    void clear();
}
