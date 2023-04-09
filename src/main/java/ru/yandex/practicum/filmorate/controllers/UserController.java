package ru.yandex.practicum.filmorate.controllers;

import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(method={RequestMethod.POST,RequestMethod.PUT})
    @ResponseBody
    public User merge(@Valid @RequestBody User user) {
        users.add(user);
        return user;
    }
}
