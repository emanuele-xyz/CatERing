create table kitchen_procedures
(
    id   int auto_increment
        primary key,
    name tinytext         null,
    type char default 'r' not null
)
    charset = utf8;

INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (1, 'Vitello tonnato', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (2, 'Carpaccio di spada', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (3, 'Alici marinate', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (4, 'Insalata di riso', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (5, 'Penne al sugo di baccalà', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (6, 'Pappa al pomodoro', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (7, 'Hamburger con bacon e cipolla caramellata', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (8, 'Salmone al forno', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (9, 'Croissant', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (10, 'Pane al cioccolato', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (11, 'Girelle all''uvetta', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (12, 'Panini al latte', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (13, 'Biscotti di pasta frolla', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (14, 'Lingue di gatto', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (15, 'Bigné farciti', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (16, 'Pizzette', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (17, 'Tramezzini', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (18, 'Sorbetto al limone', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (19, 'Torta Saint Honoré', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (20, 'Risotto alla zucca', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (21, 'Crepes con crema di cioccolato', 'r');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (22, 'Crepes', 'p');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (23, 'Crema di cioccolato', 'p');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (24, 'Cioccolato fondente', 'p');
INSERT INTO catering.kitchen_procedures (id, name, type) VALUES (25, 'Crepes con miele', 'r');