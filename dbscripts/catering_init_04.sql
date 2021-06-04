create table activities
(
    id                     int auto_increment
        primary key,
    summary_sheet_id       int                     not null,
    kitchen_procedure_id   int                     not null,
    doses_to_prepare       varchar(128) default '' not null,
    already_prepared_doses varchar(128) default '' not null,
    prepared_doses         varchar(128) default '' not null
);

create table events
(
    id                    int auto_increment
        primary key,
    name                  varchar(128) null,
    date_start            date         null,
    date_end              date         null,
    expected_participants int          null,
    organizer_id          int          not null
)
    charset = utf8;

create table kitchen_shifts
(
    id      int auto_increment
        primary key,
    is_full tinyint(1) default 0 not null
);

create table menufeatures
(
    menu_id int                     not null,
    name    varchar(128) default '' not null,
    value   tinyint(1)   default 0  null
)
    charset = utf8;

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

create table menus
(
    id        int auto_increment
        primary key,
    title     tinytext             null,
    owner_id  int                  null,
    published tinyint(1) default 0 null
)
    charset = utf8;

create table menusections
(
    id       int auto_increment
        primary key,
    menu_id  int      not null,
    name     tinytext null,
    position int      null
)
    charset = utf8;

create table recipes
(
    id   int auto_increment
        primary key,
    name tinytext null
)
    charset = utf8;

create table roles
(
    id   char                            not null
        primary key,
    role varchar(128) default 'servizio' not null
)
    charset = utf8;

create table services
(
    id                    int auto_increment
        primary key,
    event_id              int           not null,
    name                  varchar(128)  null,
    proposed_menu_id      int default 0 not null,
    approved_menu_id      int default 0 null,
    service_date          date          null,
    time_start            time          null,
    time_end              time          null,
    expected_participants int           null
)
    charset = utf8;

create table summary_sheets
(
    id         int auto_increment
        primary key,
    service_id int not null
);

create table tasks
(
    id               int auto_increment
        primary key,
    activity_id      int                     not null,
    kitchen_shift_id int                     not null,
    cook_id          int                     null,
    completed        tinyint(1)   default 0  not null,
    estimated_time   int          default 0  not null,
    estimated_doses  varchar(128) default '' not null
);

create table userroles
(
    user_id int              not null,
    role_id char default 's' not null
)
    charset = utf8;

create table users
(
    id       int auto_increment
        primary key,
    username varchar(128) default '' not null
)
    charset = utf8;

