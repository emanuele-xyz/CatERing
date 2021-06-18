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

INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (184, 31, 21, '', '', '', 1);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (185, 31, 22, '', '', '', 2);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (186, 31, 23, '', '', '', 3);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (187, 31, 24, '', '', '', 4);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (188, 31, 25, '', '', '', 5);
INSERT INTO catering.activities (id, summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (189, 31, 1, '12 porzioni', 'nessuna porzione', '', 0);