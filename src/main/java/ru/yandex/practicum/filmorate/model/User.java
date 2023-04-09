package ru.yandex.practicum.filmorate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import ru.yandex.practicum.filmorate.controllers.UserController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @EqualsAndHashCode.Include
    @NonNull
    final int id;
    @Email @NonNull @NotBlank String email;
    @NonNull @NotBlank String login;
    String name;
    @Past LocalDate birthday;

    public User() {
        this.id = UserController.getUserId();
    }
}
