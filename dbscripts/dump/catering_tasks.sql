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

INSERT INTO catering.tasks (id, activity_id, kitchen_shift_id, cook_id, completed, estimated_time, estimated_doses) VALUES (78, 224, 1, 4, 0, 60, '3 porzioni');