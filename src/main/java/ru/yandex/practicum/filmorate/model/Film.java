package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.yandex.practicum.filmorate.constraints.FilmDurationConstraint;
import ru.yandex.practicum.filmorate.constraints.FilmReleaseDateConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Film extends Generic {
    private static Integer filmId = 0;

    @NotBlank(message = "название не может быть пустым")
    private String name;

    @Size(max = 200, message = "максимальная длина описания — 200 символов")
    private String description;

    @FilmReleaseDateConstraint
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @FilmDurationConstraint
    private Duration duration;

    private Mpa mpa;

    private Set<Genre> genres;

    public Film(Integer id, String name, String description, LocalDate releaseDate, Duration duration, Mpa mpa, Set<Genre> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.mpa = mpa;
        this.genres = genres;
    }

    public void setId() {
        this.id = ++filmId;
    }
}
