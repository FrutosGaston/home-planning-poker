-- ROOM
create table room
(
    name varchar not null,
    id serial not null,
    description varchar default ''
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
            on delete cascade
);

create unique index guest_user_id_uindex
	on guest_user (id);

alter table guest_user
    add constraint guest_user_pk
        primary key (id);


-- Round
create table round
(
    room_id int not null
        constraint round_room_id_fk
            references room
            on delete cascade,
    id serial not null,
    title varchar
);

create unique index round_id_uindex
	on round (id);

alter table round
    add constraint round_pk
        primary key (id);

-- Estimation
create table estimation
(
    id int not null,
    name varchar not null,
    guest_user_id int not null
        constraint estimation_guest_user_id_fk
            references guest_user,
    round_id int not null
        constraint estimation_round_id_fk
            references round
);

create unique index estimation_id_uindex
	on estimation (id);

alter table estimation
    add constraint estimation_pk
        primary key (id);
