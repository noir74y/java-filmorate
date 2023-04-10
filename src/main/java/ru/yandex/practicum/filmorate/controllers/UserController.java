package ru.yandex.practicum.filmorate.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Set<User> users = new LinkedHashSet<>();

    @GetMapping()
    public Set<User> findAll() {
        return users;
    }

    @PostMapping
    @ResponseBody
    public User create(@Valid @RequestBody User user) {
        user.setName(user.getName() == null || user.getName().isBlank() ? user.getLogin() : user.getName());
        users.add(user);
        return user;
    }

    @PutMapping
    @ResponseBody
    public User update(@Valid @RequestBody User user) {
        if (users.contains(user)) {
            users.remove(user);
            create(user);
            return user;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no such user");
    }
}
