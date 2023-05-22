package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Component("H2UserDaoImpl")
@Slf4j
public class H2UserDaoImpl extends H2GenericImpl implements UserDao {

    @Override
    public Collection<User> list() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY id",
                (row, rowNum) ->
                        new User(
                                row.getInt("id"),
                                row.getString("email"),
                                row.getString("login"),
                                row.getString("mail"),
                                Objects.requireNonNull(row.getDate("birthday")).toLocalDate()
                        ));
    }

    @Override
    public Optional<User> get(Integer userId) {
        SqlRowSet row = getRowById("users", userId).orElse(null);
        return row != null ? Optional.of(new User(
                row.getInt("id"),
                row.getString("email"),
                row.getString("login"),
                row.getString("mail"),
                Objects.requireNonNull(row.getDate("birthday")).toLocalDate())
        ) : Optional.empty();
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
        return getRowById("users", userId).isPresent();
    }

    @Override
    public void addFriend(Integer userId, Integer friendId) {
        jdbcTemplate.update("INSERT INTO friends (user_id, friend_id) VALUES (?, ?)", userId, friendId);
    }

    @Override
    public void deleteFriend(Integer userId, Integer friendId) {
        jdbcTemplate.update("DELETE FROM friends WHERE user_id = ? AND friend_id = ?", userId, friendId);
    }

    @Override
    public Set<Integer> listUserFriends(Integer userId) {
        return null;
    }

    @Override
    public void clear() {
        jdbcTemplate.update("TRUNCATE TABLE users");
        jdbcTemplate.update("TRUNCATE TABLE friends");
    }
}
