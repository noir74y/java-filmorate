package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.constraints.FilmDurationConstraint;
import ru.yandex.practicum.filmorate.constraints.FilmReleaseDateConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDate;

@Data
@Builder
public class Film extends GenericModel {
    private static Integer filmId = 0;

    @NotBlank(message = "название не может быть пустым")
    private String name;

    @Size(max = 200, message = "максимальная длина описания — 200 символов")
    private String description;

    @FilmReleaseDateConstraint
    private LocalDate releaseDate;

    @FilmDurationConstraint
    private Duration duration;

    public void setId() {
        this.id = ++filmId;
    }
}
