create table aprozar.this_order
(
    id        int auto_increment
        primary key,
    id_client int null,
    id_produs int null,
    total     int null,
    quantity  int null,
    constraint this_order_client_id_fk
        foreign key (id_client) references aprozar.client (id),
    constraint this_order_product_id_fk
        foreign key (id_produs) references aprozar.product (id)
);

