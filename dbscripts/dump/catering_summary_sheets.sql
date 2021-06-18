create table summary_sheets
(
    id         int auto_increment
        primary key,
    service_id int not null
);

INSERT INTO catering.summary_sheets (id, service_id) VALUES (31, 9);