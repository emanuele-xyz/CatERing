create table kitchen_shifts
(
    id      int auto_increment
        primary key,
    is_full tinyint(1) default 0 not null
);

INSERT INTO catering.kitchen_shifts (id, is_full) VALUES (1, 0);
INSERT INTO catering.kitchen_shifts (id, is_full) VALUES (2, 0);