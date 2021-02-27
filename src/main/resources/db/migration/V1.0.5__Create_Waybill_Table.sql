create table waybill
(
    id bigint not null
        constraint waybill_pkey
            primary key,
    count integer,
    product_id bigint not null
        constraint fk31yt8sralcw5byao3fa4ga1kn
            references product,
    user_id bigint not null
        constraint fkjn7fayqr0dcropj0anxprk3gp
            references users
);

alter table waybill owner to postgres;

