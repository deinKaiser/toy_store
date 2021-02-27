create table users
(
    id bigint not null
        constraint users_pkey
            primary key,
    email varchar(255),
    name varchar(255),
    phone_number varchar(255)
);

alter table users owner to postgres;

