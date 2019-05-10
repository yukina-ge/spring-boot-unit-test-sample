CREATE SCHEMA IF NOT EXISTS "user";

CREATE TABLE IF NOT EXISTS "user".users(
    id bigserial primary key,
    name varchar(255) not null,
    kana varchar(255) not null,
    created_at timestamp not null as current_timestamp
);