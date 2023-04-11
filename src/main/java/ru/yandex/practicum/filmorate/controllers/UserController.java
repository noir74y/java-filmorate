package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();

    @GetMapping()
    public Collection<User> get() {
        log.info("get users response {}", users);
        return users.values();
    }

    @PostMapping
    @ResponseBody
    public User create(@Valid @RequestBody User user) {
        log.info("user create request {}", user);
        user.setId();
        user.setName(user.getName() == null || user.getName().isBlank() ? user.getLogin() : user.getName());
        users.put(user.getId(), user);
        log.info("user create response {}", user);
        return user;
    }

    @PutMapping
    @ResponseBody
    public User update(@Valid @RequestBody User user) {
        log.info("user update request {}", user);
        if (users.containsKey(user.getId())) {
            user.setName(user.getName() == null || user.getName().isBlank() ? user.getLogin() : user.getName());
            users.replace(user.getId(), user);
            log.info("user update response {}", user);
            return user;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no such user");
    }
}
