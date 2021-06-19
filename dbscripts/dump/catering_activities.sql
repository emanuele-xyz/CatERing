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

INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (219, 32, 21, '', '', '', 1);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (220, 32, 22, '', '', '', 2);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (221, 32, 23, '', '', '', 3);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (222, 32, 24, '', '', '', 4);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (223, 32, 25, '', '', '', 5);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (224, 32, 1, '12 porzioni', 'nessuna porzione', '', 0);