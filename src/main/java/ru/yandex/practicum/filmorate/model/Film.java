package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.filmorate.constraints.FilmDurationConstraint;
import ru.yandex.practicum.filmorate.constraints.FilmReleaseDateConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
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

    public Film() {
    }

    public void setId() {
        this.id = ++filmId;
    }
}
