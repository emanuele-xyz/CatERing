create table menuitems
(
    id          int auto_increment
        primary key,
    menu_id     int      not null,
    section_id  int      null,
    description tinytext null,
    recipe_id   int      not null,
    position    int      null
)
    charset = utf8;

INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (96, 80, 0, 'Croissant vuoti', 9, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (97, 80, 0, 'Croissant alla marmellata', 9, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (98, 80, 0, 'Pane al cioccolato mignon', 10, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (99, 80, 0, 'Panini al latte con prosciutto crudo', 12, 4);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (100, 80, 0, 'Panini al latte con prosciutto cotto', 12, 5);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (101, 80, 0, 'Panini al latte con formaggio spalmabile alle erbe', 12, 6);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (102, 80, 0, 'Girelle all''uvetta mignon', 11, 3);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (103, 82, 0, 'Biscotti', 13, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (104, 82, 0, 'Lingue di gatto', 14, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (105, 82, 0, 'Bigné alla crema', 15, 3);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (106, 82, 0, 'Bigné al caffè', 15, 4);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (107, 82, 0, 'Pizzette', 16, 5);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (108, 82, 0, 'Croissant al prosciutto crudo mignon', 9, 6);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (109, 82, 0, 'Tramezzini tonno e carciofini mignon', 17, 7);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (112, 86, 41, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (113, 86, 41, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (114, 86, 41, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (115, 86, 42, 'Penne alla messinese', 5, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (116, 86, 42, 'Risotto alla zucca', 20, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (117, 86, 43, 'Salmone al forno', 8, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (118, 86, 44, 'Sorbetto al limone', 18, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (119, 86, 44, 'Torta Saint Honoré', 19, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (121, 89, 47, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (122, 89, 47, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (123, 89, 47, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (124, 89, 49, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (125, 89, 49, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (126, 89, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (127, 89, 0, 'Penne al sugo di baccalà', 5, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (135, 91, 53, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (136, 91, 53, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (137, 91, 53, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (138, 91, 55, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (139, 91, 55, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (140, 91, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (141, 91, 0, 'Penne al sugo di baccalà', 5, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (142, 92, 56, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (143, 92, 56, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (144, 92, 56, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (145, 92, 58, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (146, 92, 58, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (147, 92, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (148, 92, 0, 'Penne al sugo di baccalà', 5, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (152, 93, 61, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (153, 93, 61, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (154, 93, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (155, 93, 0, 'Penne al sugo di baccalà', 5, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (159, 94, 64, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (160, 94, 64, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (161, 94, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (162, 94, 0, 'Penne al sugo di baccalà', 5, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (163, 94, 0, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (164, 94, 0, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (165, 94, 0, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (166, 95, 65, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (167, 95, 65, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (168, 95, 65, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (169, 95, 67, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (170, 95, 67, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (171, 95, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (172, 95, 0, 'Penne al sugo di baccalà', 5, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (173, 96, 68, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (174, 96, 68, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (175, 96, 68, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (176, 96, 70, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (177, 96, 70, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (178, 96, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (179, 96, 0, 'Penne al sugo di baccalà', 5, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (180, 97, 71, 'Vitello tonnato', 1, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (181, 97, 71, 'Carpaccio di spada', 2, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (182, 97, 71, 'Alici marinate', 3, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (183, 97, 73, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (184, 97, 73, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (185, 97, 0, 'Insalata di riso', 4, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (186, 97, 0, 'Penne al sugo di baccalà', 5, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (187, 98, 76, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (188, 98, 74, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (189, 98, 74, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (190, 98, 75, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (191, 98, 0, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (192, 98, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (193, 98, 74, 'Penne al sugo di baccalà', 5, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (194, 99, 77, 'Vitello tonnato', 1, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (195, 99, 77, 'Carpaccio di spada', 2, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (196, 99, 77, 'Alici marinate', 3, 2);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (197, 99, 79, 'Hamburger con bacon e cipolla caramellata', 7, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (198, 99, 79, 'Salmone al forno', 8, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (199, 99, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (200, 99, 0, 'Penne al sugo di baccalà', 5, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (202, 100, 80, 'Carpaccio di spada', 2, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (203, 100, 80, 'Alici marinate', 3, 1);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (205, 100, 82, 'Salmone al forno', 8, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (206, 100, 0, 'Insalata di riso', 4, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (208, 101, 0, 'Crepes con crema di cioccolato', 21, 0);
INSERT INTO catering.menuitems (id, menu_id, section_id, description, recipe_id, position) VALUES (209, 101, 0, 'Crepes con miele', 25, 1);