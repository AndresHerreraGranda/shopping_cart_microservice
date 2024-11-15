CREATE DATABASE shopping_cart;

\c shopping_cart;

CREATE SCHEMA IF NOT EXISTS schema_shopping_cart;

SET search_path TO schema_shopping_cart;

create table if not exists customer
(
    id serial primary key,
    name varchar(100),
    email varchar(100),
    password varchar(100)
);