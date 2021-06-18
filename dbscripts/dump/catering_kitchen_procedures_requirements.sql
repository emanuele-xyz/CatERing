create table kitchen_procedures_requirements
(
    kitchen_procedure_id    int not null,
    required_preparation_id int not null
);

INSERT INTO catering.kitchen_procedures_requirements (kitchen_procedure_id, required_preparation_id) VALUES (21, 22);
INSERT INTO catering.kitchen_procedures_requirements (kitchen_procedure_id, required_preparation_id) VALUES (21, 23);
INSERT INTO catering.kitchen_procedures_requirements (kitchen_procedure_id, required_preparation_id) VALUES (23, 24);
INSERT INTO catering.kitchen_procedures_requirements (kitchen_procedure_id, required_preparation_id) VALUES (25, 22);