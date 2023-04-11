package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import ru.yandex.practicum.filmorate.constraints.FilmDurationConstraint;
import ru.yandex.practicum.filmorate.constraints.FilmReleaseDateConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDate;

@Data
public class Film {
    private static Integer filmId = 0;

    private Integer id;

    @NonNull @NotBlank private String name;

    @Size(max=200) private String description;

    @FilmReleaseDateConstraint
    private LocalDate releaseDate;

    @FilmDurationConstraint
    private Duration duration;

    public void setId() {
        this.id = ++filmId;
    }
}
