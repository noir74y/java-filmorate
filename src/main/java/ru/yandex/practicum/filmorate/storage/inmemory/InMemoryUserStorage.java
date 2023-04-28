package ru.yandex.practicum.filmorate.storage.inmemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.interfaces.UserStorage;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private final HashMap<Object, Set<Integer>> friends = new HashMap<>();

    @Override
    public Collection<User> list() {
        log.info("get users response {}", users);
        return users.values();
    }

    @Override
    public User get(Integer userId) {
        return users.get(userId);
    }

    @Override
    public User create(User user) {
        log.info("user create request {}", user);
        user.setId();
        setUserName(user);
        users.put(user.getId(), user);
        log.info("user create response {}", user);
        return user;
    }

    @Override
    public User update(User user) {
        log.info("user update request {}", user);
        if (users.containsKey(user.getId())) {
            setUserName(user);
            users.replace(user.getId(), user);
            log.info("user update response {}", user);
            return user;
        }
        log.error("no such user {}", user);
        throw new NotFoundException("no such user");
    }

    private void setUserName(User user) {
        user.setName(user.getName() == null || user.getName().isBlank() ? user.getLogin() : user.getName());
    }

    @Override
    public void addFriend(Integer userHostId, Integer userGuestId) {
        Set<Integer> friendsList = friends.getOrDefault(userHostId, new HashSet<>());
        friendsList.add(userGuestId);
        friends.putIfAbsent(userHostId, friendsList);
    }

    @Override
    public void deleteFriend(Integer userHostId, Integer userGuestId) {
        friends.get(userHostId).remove(userGuestId);
    }

    @Override
    public Set<Integer> getFriends(Integer userId) {
        return friends.get(userId);
    }
}
