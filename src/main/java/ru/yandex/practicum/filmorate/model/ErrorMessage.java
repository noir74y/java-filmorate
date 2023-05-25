package ru.yandex.practicum.filmorate.model;

import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage extends Generic {
    private String cause;
    private String message;
}