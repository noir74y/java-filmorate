package ru.yandex.practicum.filmorate.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Genre extends Generic {
    private Integer id;
    private String name;
}
