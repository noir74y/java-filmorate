# java-filmorate
Repository for Filmorate project.

## ER-diagram



### Query examples
```
-- список пользователей, лайкнувших фильм <film_id>
SELECT users.*
FROM likes
JOIN users ON users.id = likes.user_id
WHERE likes.film_id = <film_id>;
```
```
-- список фильмов, которые лайкнул пользователь <user_id>
SELECT films.*
FROM films
JOIN likes ON likes.film_id = films.id AND user_id = <user_id>;
```
```
-- список жанров, относящихся к фильму <film_id>
SELECT genre.*
FROM genres
JOIN genre ON genre.id = genres.genre_id AND genres.film_id = <film_id>;
```
```
-- список фильмов в жанре <genre_name>
SELECT films.*
FROM films
JOIN genres ON genres.film_id = films.id AND genres.genre_id = genre.id
JOIN genre ON genre.name = <genre_name>;
```
```
-- список друзей пользователя <user_id>
SELECT users.*
FROM users
JOIN friends ON friends.friend_id = users.id AND friends.user_id = <user_id>;
```
```
-- список пользователей, имеющих друга <friend_id>
SELECT users.*
FROM users
JOIN friends ON friends.user_id = users.id AND friends.user_id = <friend_id>;
```
