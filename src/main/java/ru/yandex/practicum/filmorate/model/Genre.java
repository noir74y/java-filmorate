package ru.yandex.practicum.filmorate.model;

import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Genre extends Generic{
    @Setter@Getter
    private String name;

    public Genre(Integer id, String name) {
        super(id);
        this.id = id;
        this.name = name;
    }
}
