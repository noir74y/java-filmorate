package ru.yandex.practicum.filmorate.storage.inmemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.interfaces.UserStorage;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private final HashMap<Integer, Set<Integer>> friends = new HashMap<>();

    @Override
    public Collection<User> list() {
        log.info("get users response {}", users);
        return users.values();
    }

    @Override
    public User get(Integer userId) {
        if (isUserExists(userId))
            return users.get(userId);

        log.error("no such userId {}", userId);
        throw new NotFoundException("no such userId");
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
        if (isUserExists(user.getId())) {
            setUserName(user);
            users.replace(user.getId(), user);
            log.info("user update response {}", user);
            return user;
        }
        log.error("no such user {}", user);
        throw new NotFoundException("no such user");
    }

    @Override
    public boolean isUserExists(Integer userId) {
        return users.containsKey(userId);
    }

    private void setUserName(User user) {
        user.setName(user.getName() == null || user.getName().isBlank() ? user.getLogin() : user.getName());
    }

    @Override
    public void addFriend(Integer userHostId, Integer userGuestId) {
        if (isUserExists(userHostId) && isUserExists(userGuestId)) {
            Set<Integer> friendsList = friends.getOrDefault(userHostId, new HashSet<>());
            friendsList.add(userGuestId);
            friends.putIfAbsent(userHostId, friendsList);
        } else processNotFoundException(userHostId, userGuestId);
    }

    @Override
    public void deleteFriend(Integer userHostId, Integer userGuestId) {
        if (isUserExists(userHostId) && isUserExists(userGuestId))
            friends.get(userHostId).remove(userGuestId);
        else processNotFoundException(userHostId, userGuestId);
    }

    private void processNotFoundException(Integer userHostId, Integer userGuestId) {
        if (!isUserExists(userHostId)) {
            log.error("no such userHostId {}", userHostId);
            throw new NotFoundException("no such filmId");
        } else if (!isUserExists(userGuestId)) {
            log.error("no such userGuestId {}", userGuestId);
            throw new NotFoundException("no such userGuestId");
        }
    }

    @Override
    public Set<Integer> getFriends(Integer userId) {
        return Optional.ofNullable(friends.get(userId)).orElse(new HashSet<>());
    }
}
