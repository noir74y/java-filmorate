package ru.yandex.practicum.filmorate.dao.implementations.InMemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component("InMemoryUserDaoImpl")
@Slf4j
public class UserDaoImpl implements UserDao {
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
        throw new NotFoundException("no such userId", String.valueOf(userId));
    }

    @Override
    public User create(User user) {
        log.info("user create request {}", user);
        user.setId();
        setUserName(user);
        users.put(user.getId(), user);
        friends.put(user.getId(), new HashSet<>());
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
        throw new NotFoundException("no such userId", String.valueOf(user.getId()));
    }

    @Override
    public boolean isUserExists(Integer userId) {
        return users.containsKey(userId);
    }

    @Override
    public void addFriend(Integer userHostId, Integer userFriendId) {
        if (isUserExists(userHostId) && isUserExists(userFriendId)) {
            friends.get(userHostId).add(userFriendId);
        } else processNotFoundException(userHostId, userFriendId);
    }

    @Override
    public void deleteFriend(Integer userHostId, Integer userFriendId) {
        if (isUserExists(userHostId) && isUserExists(userFriendId))
            friends.get(userHostId).remove(userFriendId);
        else processNotFoundException(userHostId, userFriendId);
    }

    @Override
    public Set<Integer> getFriends(Integer userId) {
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

    private void processNotFoundException(Integer userHostId, Integer userFriendId) {
        if (!isUserExists(userHostId)) {
            log.error("no such userHostId {}", userHostId);
            throw new NotFoundException("no such userHostId", String.valueOf(userHostId));
        } else if (!isUserExists(userFriendId)) {
            log.error("no such userFriendId {}", userFriendId);
            throw new NotFoundException("no such userFriendId", String.valueOf(userFriendId));
        }
    }
}
