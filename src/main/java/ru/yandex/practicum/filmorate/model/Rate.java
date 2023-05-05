package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Rate implements Comparable<Rate> {
    int filmId;
    Set<Integer> likedUsersId;

    public Rate() {
    }

    @Override
    public int compareTo(Rate o) {
        return o.likedUsersId.size() - likedUsersId.size();
    }
}
