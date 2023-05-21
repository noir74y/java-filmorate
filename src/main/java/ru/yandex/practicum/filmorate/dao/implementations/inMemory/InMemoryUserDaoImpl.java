package ru.yandex.practicum.filmorate.dao.implementations.inMemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component("InMemoryUserDaoImpl")
@Slf4j
@Primary
public class InMemoryUserDaoImpl implements UserDao {
    protected final Map<Integer, User> users = new HashMap<>();
    protected final HashMap<Integer, Set<Integer>> friends = new HashMap<>();

    @Override
    public Collection<User> list() {
        return users.values();
    }

    @Override
    public User get(Integer userId) {
        return users.get(userId);
    }

    @Override
    public User create(User user) {
        user.setId();
        setUserName(user);
        users.put(user.getId(), user);
        friends.put(user.getId(), new HashSet<>());
        return user;
    }

    @Override
    public User update(User user) {
        setUserName(user);
        users.replace(user.getId(), user);
        return user;
    }

    @Override
    public boolean isUserExists(Integer userId) {
        return users.containsKey(userId);
    }

    @Override
    public void addFriend(Integer userId, Integer friendId) {
        friends.get(userId).add(friendId);
        friends.get(friendId).add(userId);
    }

    @Override
    public void deleteFriend(Integer userId, Integer friendId) {
        friends.get(userId).remove(friendId);
        friends.get(friendId).remove(userId);
    }

    @Override
    public Set<Integer> listUserFriends(Integer userId) {
        return Optional.ofNullable(friends.get(userId)).orElse(new HashSet<>());
    }

    @Override
    public void clear() {
        users.clear();
        friends.clear();
    }

    private void setUserName(User user) {
        user.setName(user.getName() == null || user.getName().isBlank() ? user.getLogin() : user.getName());
    }
}
