CREATE TABLE auto_user
(
    id       SERIAL PRIMARY KEY,
    login    varchar        not null,
    password varchar        not null
);