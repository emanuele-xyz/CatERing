create table kitchen_shifts
(
    id         int auto_increment
        primary key,
    is_full    tinyint(1) default 0 not null,
    date       date                 not null,
    time_start time                 not null,
    time_end   time                 not null
);

INSERT INTO catering.kitchen_shifts (id, is_full, date, time_start, time_end) VALUES (1, 0, '2022-06-18', '10:00:00', '12:00:00');
INSERT INTO catering.kitchen_shifts (id, is_full, date, time_start, time_end) VALUES (2, 0, '2022-06-18', '14:00:00', '17:00:00');
INSERT INTO catering.kitchen_shifts (id, is_full, date, time_start, time_end) VALUES (3, 0, '2022-06-18', '06:00:00', '09:00:00');
INSERT INTO catering.kitchen_shifts (id, is_full, date, time_start, time_end) VALUES (4, 0, '2022-06-18', '09:00:00', '10:00:00');