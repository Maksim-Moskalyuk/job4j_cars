CREATE TABLE auto_post
(
    id           SERIAL PRIMARY KEY,
    description  varchar   not null,
    created      timestamp not null,
    file_id      int references auto_user(id)
);