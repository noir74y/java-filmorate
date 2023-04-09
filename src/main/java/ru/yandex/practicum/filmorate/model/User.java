package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import ru.yandex.practicum.filmorate.controllers.UserController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    private static int userId = 0;
    @EqualsAndHashCode.Include
    @NonNull
    final int id;
    @Email @NonNull @NotBlank String email;
    @NonNull @NotBlank String login;
    String name;
    @Past LocalDate birthday;

    public User() {
        this.id = ++userId;
    }
}
