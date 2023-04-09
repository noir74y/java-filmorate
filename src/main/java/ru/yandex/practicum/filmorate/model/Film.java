package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import ru.yandex.practicum.filmorate.controllers.FilmController;

import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Film {
    private static int filmId = 0;
    @EqualsAndHashCode.Include
    @NonNull
    final int id;
    @NonNull @NotBlank String name;
    String description;
    LocalDate releaseDate;
    Duration duration;

    public Film() {
        this.id = ++filmId;
    }
}
