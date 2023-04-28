package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Like implements Comparable<Like> {
    int filmId;
    Set<Integer> userIdSet;

    @Override
    public int compareTo(Like o) {
        return o.userIdSet.size() - userIdSet.size();
    }
}
