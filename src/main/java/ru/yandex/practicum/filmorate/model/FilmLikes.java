package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmLikes implements Comparable<FilmLikes> {
    int filmId;
    Set<Integer> likedUsersId;

    @Override
    public int compareTo(FilmLikes o) {
        return o.likedUsersId.size() - likedUsersId.size();
    }
}
