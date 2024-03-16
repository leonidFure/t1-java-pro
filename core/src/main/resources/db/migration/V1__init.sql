create table items (id bigserial primary key, title varchar(255), secret varchar(255));

insert into items (title, secret) values ('item1', 'secret1'), ('item2', 'secret2');

create table transactions (uuid varchar(255) primary key, user_id varchar(255), receiver_number varchar(255), product_id varchar(255), product_type varchar(255), transaction_id varchar(255));