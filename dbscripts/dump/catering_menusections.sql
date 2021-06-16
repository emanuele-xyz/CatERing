create table menusections
(
    id       int auto_increment
        primary key,
    menu_id  int      not null,
    name     tinytext null,
    position int      null
)
    charset = utf8;

INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (41, 86, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (42, 86, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (43, 86, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (44, 86, 'Dessert', 3);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (45, 87, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (47, 89, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (48, 89, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (49, 89, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (53, 91, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (54, 91, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (55, 91, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (56, 92, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (57, 92, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (58, 92, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (61, 93, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (63, 94, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (64, 94, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (65, 95, 'Hors d''Oeuvres', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (66, 95, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (67, 95, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (68, 96, 'Antipasti', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (69, 96, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (70, 96, 'Secondi', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (71, 97, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (72, 97, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (73, 97, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (74, 98, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (75, 98, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (76, 98, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (77, 99, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (78, 99, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (79, 99, 'Secondi', 2);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (80, 100, 'Antipasti', 0);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (81, 100, 'Primi', 1);
INSERT INTO catering.menusections (id, menu_id, name, position) VALUES (82, 100, 'Secondi', 2);