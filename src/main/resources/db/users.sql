CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE
);

CREATE TABLE products
(
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT REFERENCES users (id) NOT NULL,
    product_number BIGINT                       NOT NULL,
    balance        NUMERIC                      NOT NULL,
    type           VARCHAR(255)                 NOT NULL
)