create table menus
(
    id        int auto_increment
        primary key,
    title     tinytext             null,
    owner_id  int                  null,
    published tinyint(1) default 0 null
)
    charset = utf8;

INSERT INTO catering.menus (id, title, owner_id, published) VALUES (80, 'Coffee break mattutino', 2, 1);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (82, 'Coffee break pomeridiano', 2, 1);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (86, 'Cena di compleanno pesce', 3, 1);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (89, 'Titolo Nuovo', 2, 1);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (91, 'Menu da copiare', 2, 1);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (92, 'Menu da copiare', 2, 0);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (93, 'Menu Pinco Pallino', 2, 0);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (94, 'Menu Pinco Pallino', 2, 0);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (95, 'Menu Pinco Pallino', 2, 0);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (96, 'Menu Pinco Pallino', 2, 0);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (97, 'Menu Pinco Pallino', 2, 0);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (98, 'Menu Pinco Pallino', 2, 0);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (99, 'Menu Pinco Pallino', 2, 0);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (100, 'Menu Pinco Pallino', 2, 0);
INSERT INTO catering.menus (id, title, owner_id, published) VALUES (101, 'Selezione di Crepes', 2, 1);