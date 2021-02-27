create table warehouse
(
    id bigint not null
        constraint warehouse_pkey
            primary key,
    adress varchar(255),
    name varchar(255),
    phone varchar(255)
);

alter table warehouse owner to postgres;

