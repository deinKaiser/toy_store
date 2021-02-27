create table product
(
    id bigint not null
        constraint product_pkey
            primary key,
    count integer,
    name varchar(255),
    type_id bigint not null
        constraint fkq3fvcsydiaotwy3iqn1erqsfd
            references type,
    warehouse_id bigint not null
        constraint fkk6edvfdkq61mjhltx856ncs3x
            references warehouse
);

alter table product owner to postgres;

