create table type
(
    id   bigint not null
        constraint type_pkey
            primary key,
    name varchar(255)
);

alter table type owner to postgres;
