
-- ROOM
create table room
(
    name varchar not null,
    id serial not null,
    description varchar default '',
    created_at timestamp default NOW()
);

create unique index room_id_uindex
	on room (id);

alter table room
    add constraint room_pk
        primary key (id);


-- GUEST USER
create table guest_user
(
    id serial not null,
    name varchar not null,
    room_id int not null
        constraint guest_user_room_id_fk
            references room (id)
            on delete cascade,
    created_at timestamp default NOW()
);

create unique index guest_user_id_uindex
	on guest_user (id);

alter table guest_user
    add constraint guest_user_pk
        primary key (id);


-- TASK
create table task
(
    room_id int not null
        constraint task_room_id_fk
            references room
            on delete cascade,
    id serial not null,
    final_estimation varchar,
    title varchar,
    created_at timestamp default NOW()
);

create unique index task_id_uindex
	on task (id);

alter table task
    add constraint task_pk
        primary key (id);

-- ESTIMATION
create table estimation
(
    id serial not null,
    name varchar not null,
    guest_user_id int not null
        constraint estimation_guest_user_id_fk
            references guest_user,
    task_id int not null
        constraint estimation_task_id_fk
            references task,
    created_at timestamp default NOW()
);

create unique index estimation_id_uindex
	on estimation (id);

alter table estimation
    add constraint estimation_pk
        primary key (id);
