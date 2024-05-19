--liquibase formatted sql
--changeset Minh Hoang Pham:0.0.1-01

CREATE TABLE password
(
    id       UUID                        NOT NULL primary key default gen_random_uuid(),
    created  TIMESTAMP WITHOUT TIME ZONE NOT NULL             default now(),
    updated  TIMESTAMP WITHOUT TIME ZONE                      default now(),
    name     VARCHAR                     NOT NULL UNIQUE,
    password VARCHAR
);
