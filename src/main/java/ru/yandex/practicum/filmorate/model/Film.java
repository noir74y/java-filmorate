package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.constraints.FilmDurationConstraint;
import ru.yandex.practicum.filmorate.constraints.FilmReleaseDateConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDate;

@Data
public class Film {
    private static Integer filmId = 0;

    private Integer id;

    @NotNull(message = "name can not be null")
    @NotBlank(message = "name can not be blank or empty")
    private String name;

    @Size(max = 200, message = "description is too long")
    private String description;

    @FilmReleaseDateConstraint
    private LocalDate releaseDate;

    @FilmDurationConstraint
    private Duration duration;

    public void setId() {
        this.id = ++filmId;
    }
}
