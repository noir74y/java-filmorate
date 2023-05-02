package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.constraints.NoSpacesInUserLoginConstraint;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class User extends GenericModel {
    private static Integer userId = 0;

    @Email
    @NotBlank(message = "электронная почта не может быть пустой")
    private String email;

    @NotBlank(message = "логин не может быть пустым")
    @NoSpacesInUserLoginConstraint(message = "логин не может содержать пробелы")
    private String login;

    private String name;

    @PastOrPresent(message = "дата рождения не может быть в будущем")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    public void setId() {
        this.id = ++userId;
    }
}
