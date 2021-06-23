
-- ROOM
create table room
(
    title varchar not null,
    id serial not null,
    description varchar default '',
    created_at timestamp default NOW(),
    deck_id int not null
        constraint romm_deck_id_fk
            references deck (id)
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
    title varchar,
    created_at timestamp default NOW()
);

create unique index task_id_uindex
	on task (id);

alter table task
    add constraint task_pk
        primary key (id);

-- DECK
create table deck
(
    id serial not null,
    name varchar not null,
    created_at timestamp default NOW()
);

create unique index deck_id_uindex
	on deck (id);

alter table deck
    add constraint deck_pk
        primary key (id);

-- CARD
create table card
(
    id serial not null,
    value varchar(3) not null,
    deck_id int not null
        constraint card_deck_id_fk
            references deck,
    created_at timestamp default NOW()
);

create unique index card_id_uindex
	on card (id);

alter table card
    add constraint card_pk
        primary key (id);


-- ESTIMATION
create table estimation
(
    id serial not null,
    card_id int not null
        constraint estimation_card_id_fk
            references card,
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


-- ADD ESTIMATION TO TASK
alter table task
    add estimation_id int
        constraint task_estimation_id_fk
            references estimation;

-- ADD ACTIVE TO ESTIMATIONS
alter table estimation
    add active boolean default true;

-- ADD SELECTED TASK TO ROOM
alter table room
    add selected_task_id int
        constraint room_task_id_fk
            references task (id);

-- ADD CODE TO ROOM
alter table room
    add code varchar(4) not null;
create index room_code_index on room (code);

-- ADD UUID TO ROOM
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
alter table room add uuid uuid default uuid_generate_v4();
create index room_uuid_index on room (uuid);

INSERT INTO public.deck (id, name, created_at) VALUES (1, 'Fibonacci', '2021-05-22 20:29:46.626436');
INSERT INTO public.card (id, value, deck_id, created_at) VALUES (1, '0', 1, '2021-05-22 20:30:43.360059');
INSERT INTO public.card (id, value, deck_id, created_at) VALUES (2, '1', 1, '2021-05-22 20:30:43.360059');
INSERT INTO public.card (id, value, deck_id, created_at) VALUES (3, '2', 1, '2021-05-22 20:30:43.360059');
INSERT INTO public.card (id, value, deck_id, created_at) VALUES (4, '3', 1, '2021-05-22 20:30:43.360059');
INSERT INTO public.card (id, value, deck_id, created_at) VALUES (5, '5', 1, '2021-05-22 20:30:43.360059');
INSERT INTO public.card (id, value, deck_id, created_at) VALUES (6, '8', 1, '2021-05-22 20:30:43.360059');
INSERT INTO public.card (id, value, deck_id, created_at) VALUES (7, '13', 1, '2021-05-22 20:30:43.360059');
INSERT INTO public.card (id, value, deck_id, created_at) VALUES (8, '21', 1, '2021-05-22 20:30:43.360059');
INSERT INTO public.card (id, value, deck_id, created_at) VALUES (9, '40', 1, '2021-05-22 20:30:43.360059');