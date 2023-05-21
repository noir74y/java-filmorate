package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Set;

@Component("H2UserDaoImpl")
@Slf4j
public class H2UserDaoImpl implements UserDao {
    @Override
    public Collection<User> list() {
        return null;
    }

    @Override
    public User get(Integer userId) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean isUserExists(Integer userId) {
        return false;
    }

    @Override
    public void addFriend(Integer userId, Integer friendId) {

    }

    @Override
    public void deleteFriend(Integer userId, Integer friendId) {

    }

    @Override
    public void addFriendship(Integer userId1, Integer userId2) {

    }

    @Override
    public void deleteFriendship(Integer userId1, Integer userId2) {

    }

    @Override
    public Set<Integer> listUserFriends(Integer userId) {
        return null;
    }

    @Override
    public void clear() {

    }
}
