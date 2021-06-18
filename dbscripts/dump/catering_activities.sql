create table activities
(
    id                     int auto_increment
        primary key,
    summary_sheet_id       int                     not null,
    kitchen_procedure_id   int                     not null,
    doses_to_prepare       varchar(128) default '' not null,
    already_prepared_doses varchar(128) default '' not null,
    prepared_doses         varchar(128) default '' not null,
    position               int                     not null
);

