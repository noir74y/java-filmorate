package ru.yandex.practicum.filmorate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import ru.yandex.practicum.filmorate.controllers.FilmController;

import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.time.LocalDate;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Film {
    @EqualsAndHashCode.Include
    @NonNull
    final int id;
    @NonNull @NotBlank String name;
    String description;
    LocalDate releaseDate;
    Duration duration;

    public Film() {
        this.id = FilmController.getFilmId();
    }
}
