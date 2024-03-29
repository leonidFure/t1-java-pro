create table if not exists limits
(
    user_id             varchar(255) primary key,
    amount              numeric   not null,
    update_datetime     timestamp not null,
    last_transaction_id varchar(255)
);

create table if not exists coin_operations
(
    transaction_id varchar(255) primary key,
    user_id        varchar(255) not null references limits (user_id),
    amount         numeric      not null,
    datetime       timestamp    not null,
    based_on_key   varchar      not null default '__start'
);

create unique index if not exists uidx_unique_based_on
    on coin_operations (user_id, based_on_key);

