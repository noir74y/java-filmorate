package ru.yandex.practicum.filmorate.dao.implementations.inMemory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.implementations.generic.GenericUserDaoImpl;

@Component("InMemoryUserDaoImpl")
@Primary
public class InMemoryUserDaoImpl extends GenericUserDaoImpl {
    @Override
    public void addFriendship(Integer userId1, Integer userId2) {
        addFriend(userId1, userId2);
        addFriend(userId2, userId1);
    }

    @Override
    public void deleteFriendship(Integer userId1, Integer userId2) {
        deleteFriend(userId1, userId2);
        deleteFriend(userId2, userId1);
    }

    @Override
    public void addFriend(Integer userId, Integer friendId) {
        if (isUserExists(userId) && isUserExists(friendId)) {
            genericStorage.getFriends(userId).add(friendId);
        } else processNotFoundException(userId, friendId);
    }

    @Override
    public void deleteFriend(Integer userId, Integer friendId) {
        if (isUserExists(userId) && isUserExists(friendId))
            genericStorage.getFriends(userId).remove(friendId);
        else processNotFoundException(userId, friendId);
    }
}
