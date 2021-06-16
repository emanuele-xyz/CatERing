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

