package ru.yandex.practicum.filmorate.dao.interfaces;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Set;

public interface UserDao {
    Collection<User> list();

    User get(Integer userId);

    User create(User user);

    User update(User user);

    boolean isUserExists(Integer userId);

    void addFriend(Integer userId, Integer friendId);

    void deleteFriend(Integer userId, Integer friendId);

    void addFriendship(Integer userId1, Integer userId2);

    void deleteFriendship(Integer userId1, Integer userId2);

    Set<Integer> listUserFriends(Integer userId);

    void clear();
}
