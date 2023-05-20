package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class FilmLikes implements Comparable<FilmLikes> {
    int filmId;
    Set<Integer> likedUsersId;

    public FilmLikes() {
    }

    @Override
    public int compareTo(FilmLikes o) {
        return o.likedUsersId.size() - likedUsersId.size();
    }
}
