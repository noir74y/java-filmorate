package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public Collection<User> list() {
        return userService.list();
    }

    @GetMapping("/{userId}")
    public User get(@PathVariable Integer userId) {
        return userService.get(userId);
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        return userService.update(user);
    }

    @PutMapping("/{userHost}/friends/{userGuest}")
    public void addFriendship(@PathVariable Integer userHost, @PathVariable Integer userGuest) {
        userService.addFriendship(userHost, userGuest);
    }

    @DeleteMapping("/{userHost}/friends/{userGuest}")
    public void deleteFriendship(@PathVariable Integer userHost, @PathVariable Integer userGuest) {
        userService.deleteFriendship(userHost, userGuest);
    }

    @GetMapping("/{userHost}/friends")
    public Collection<User> getFriends(@PathVariable Integer userHost) {
        return userService.getFriends(userHost);
    }

    @GetMapping("/{userId1}/friends/common/{userId2}")
    public Collection<User> getCommonFriends(@PathVariable Integer userId1, @PathVariable Integer userId2) {
        return userService.getCommonFriends(userId1, userId2);
    }

}
