# java-filmorate
Repository for Filmorate project.

## ER-diagram
![ER-diagram for the project](https://github.com/noir74y/java-filmorate/blob/main/filmorate-ER.JPG)

### Query examples
```
-- список пользователей, лайкнувших фильм <film_id>
SELECT user.*
FROM like
JOIN user ON user.id = like.user_id
WHERE like.film_id = <film_id>;
```
```
-- список фильмов, которые лайкнул пользователь <user_id>
SELECT film.*
FROM film
JOIN like ON like.film_id = film.id AND user_id = <user_id>;
```
```
-- список жанров, относящихся к фильму <film_id>
SELECT genre.*
FROM genres
JOIN genre ON genre.id = genres.genre_id AND genres.film_id = <film_id>;
```
```
-- список фильмов в жанре <genre_name>
SELECT film.*
FROM film
JOIN genres ON genres.film_id = film.id AND genres.genre_id = genre.id
JOIN genre ON genre.name = <genre_name>;
```
```
-- список друзей пользователя <user_id>
SELECT user.*
FROM user
JOIN friend ON friend.friend_id = user.id AND friend.user_id = <user_id>;
```
```
-- список пользователей, имеющих друга <friend_id>
SELECT user.*
FROM user
JOIN friend ON friend.user_id = user.id AND friend.user_id = <friend_id>;
```
