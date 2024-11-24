CREATE DATABASE shopping_cart;

\c shopping_cart;

CREATE SCHEMA IF NOT EXISTS schema_shopping_cart;

SET search_path TO schema_shopping_cart;
CREATE TABLE IF NOT EXISTS schema_shopping_cart.providers
(
    id serial,
    name character varying,
    email character varying,
    password character varying,
    CONSTRAINT providers_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS schema_shopping_cart.customers
(
    id serial NOT NULL,
    name character varying,
    email character varying,
    password character varying,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS schema_shopping_cart.products
(
    id serial NOT NULL,
    name character varying,
    price double precision,
    description character varying,
    image_url character varying,
    stock integer,
    id_provider integer,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT id_provider FOREIGN KEY (id_provider)
    REFERENCES schema_shopping_cart.providers (id) MATCH SIMPLE
    );
CREATE TABLE IF NOT EXISTS schema_shopping_cart.carts
(
    id serial NOT NULL,
    id_customer integer,
    id_product integer,
    quantity integer,
    sub_total double precision,
    CONSTRAINT carts_pkey PRIMARY KEY (id),
    CONSTRAINT id_product FOREIGN KEY (id_product)
    REFERENCES schema_shopping_cart.products (id) MATCH SIMPLE,
    CONSTRAINT id_customer FOREIGN KEY (id_customer)
    REFERENCES schema_shopping_cart.customers (id) MATCH SIMPLE
    );




