CREATE TABLE products
(
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT       NOT NULL,
    product_number BIGINT       NOT NULL,
    balance        NUMERIC      NOT NULL,
    type           VARCHAR(255) NOT NULL
)