DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS films;
DROP TABLE IF EXISTS rating;

CREATE TABLE rating (
id integer PRIMARY KEY,
name VARCHAR(256)
);

CREATE TABLE films (
id INTEGER PRIMARY KEY,
name VARCHAR(256),
description text,
release_date VARCHAR(10),
duration INTEGER,
rating_id INTEGER REFERENCES rating (id)
);

CREATE TABLE genre (
id INTEGER PRIMARY KEY,
name VARCHAR(256)
);

CREATE TABLE genres (
film_id INTEGER REFERENCES films (id),
genre_id INTEGER REFERENCES genre (id)
);

CREATE TABLE users (
id INTEGER PRIMARY KEY,
email VARCHAR(256),
login VARCHAR(256),
name VARCHAR(256),
birthday VARCHAR(10)
);

CREATE TABLE friends (
user_id INTEGER REFERENCES users (id),
friend_id INTEGER REFERENCES films (id)
);

CREATE TABLE likes (
user_id INTEGER REFERENCES users (id),
film_id INTEGER REFERENCES films (id)
);
