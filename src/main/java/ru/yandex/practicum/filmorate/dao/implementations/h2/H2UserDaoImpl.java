package ru.yandex.practicum.filmorate.dao.implementations.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.interfaces.UserDao;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.*;

@Component("H2UserDaoImpl")
@Slf4j
@Primary
public class H2UserDaoImpl extends H2GenericImpl implements UserDao {

    @Override
    public Collection<User> list() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY id",
                (row, rowNum) ->
                        new User(
                                row.getInt("id"),
                                row.getString("email"),
                                row.getString("login"),
                                row.getString("name"),
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
                row.getString("name"),
                Objects.requireNonNull(row.getDate("birthday")).toLocalDate())
        ) : Optional.empty();
    }

    @Override
    public User create(User user) {
        String sql = "INSERT INTO users(email, login, name, birthday) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"});
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getName());
            stmt.setDate(4, Date.valueOf(user.getBirthday()));
            return stmt;
        }, keyHolder);

        user.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());

        return user;
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update("UPDATE users SET email = ?, login = ?, name = ?, birthday = ? WHERE id = ?",
                user.getEmail(), user.getLogin(), user.getName(), Date.valueOf(user.getBirthday()), user.getId());
        return get(user.getId()).orElse(null);
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
        return new HashSet<>(jdbcTemplate.queryForList("SELECT friend_id FROM friends WHERE user_id = ?", Integer.class, userId));
    }

    @Override
    public void clear() {
        jdbcTemplate.update("DELETE FROM friends");
        jdbcTemplate.update("DELETE FROM users");
        jdbcTemplate.update("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
    }
}
