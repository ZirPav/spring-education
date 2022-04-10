create schema dict;

create sequence dict.currency_id_seq;

create table dict.currency
(
    id   integer unique not null default nextval('dict.currency_id_seq'),
    name varchar        not null,
    PRIMARY KEY (id)
);

create sequence dict.bankbook_id_seq;

create table dict.bankbook
(
    id          integer unique not null default nextval('dict.bankbook_id_seq'),
    amount      numeric(19, 2),
    number      varchar,
    currency_id integer,
    user_id     integer        not null,
    PRIMARY KEY (id),
    FOREIGN KEY (currency_id) REFERENCES dict.currency (id)
);

create sequence dict.users_id_seq;

create table dict.users
(
    id           integer unique not null default nextval('dict.users_id_seq'),
    email        varchar        not null,
    name         varchar        not null,
    PRIMARY KEY (id)
);

alter table dict.bankbook
    add foreign key (user_id) references dict.users;