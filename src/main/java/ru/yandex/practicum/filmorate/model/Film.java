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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Film {
    private static int filmId = 0;

    @EqualsAndHashCode.Include
    @NonNull
    private final int id;

    @NonNull @NotBlank @Size(max=200) private String name;
    private String description;

    @FilmReleaseDateConstraint
    private LocalDate releaseDate;

    @FilmDurationConstraint
    private Duration duration;

    public Film() {
        this.id = ++filmId;
    }
}
