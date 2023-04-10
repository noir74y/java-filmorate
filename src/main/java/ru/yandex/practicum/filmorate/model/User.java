package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.filmorate.constraints.NoSpacesInUserLoginConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    private static int userId = 0;

    @EqualsAndHashCode.Include
    private final int id;

    @Email(message = "email is not correct")
    @NotNull(message = "email can not be null")
    @NotBlank(message = "email can not be blank or empty")
    private String email;

    @NotNull(message = "login can not be null")
    @NotBlank(message = "login can not be blank or empty")
    @NoSpacesInUserLoginConstraint
    private String login;

    private String name;

    @Past
    private LocalDate birthday;

    public User() {
        this.id = ++userId;
    }

    public void setName(String name) {
        this.name = name.isBlank() ? login : name;
    }
}
