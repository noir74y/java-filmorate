package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.constraints.NoSpacesInUserLoginConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder
public class User {
    private static Integer userId = 0;

    private Integer id;

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

    public void setId() {
        this.id = ++userId;
    }
}
