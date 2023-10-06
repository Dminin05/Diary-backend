create table verification_code
(
    id         uuid default gen_random_uuid() not null
        constraint verification_code_pk
            primary key,
    value      varchar                        not null,
    expires_at timestamp                      not null,
    email      varchar
);

alter table verification_code
    owner to postgres;

create table teacher
(
    id         uuid default gen_random_uuid() not null
        constraint teacher_pk
            primary key,
    first_name varchar                        not null,
    last_name  varchar                        not null,
    created_at timestamp                      not null,
    updated_at timestamp                      not null
);

alter table teacher
    owner to postgres;

create table admin
(
    id         uuid      default gen_random_uuid() not null
        primary key,
    first_name varchar                             not null,
    last_name  varchar                             not null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    updated_at timestamp default CURRENT_TIMESTAMP
);

alter table admin
    owner to postgres;

create table subject
(
    id    uuid default gen_random_uuid() not null
        primary key,
    title text                           not null
);

alter table subject
    owner to postgres;

create table teachers_subjects
(
    teacher_id uuid not null
        constraint teacher_fk
            references teacher
            on update cascade on delete cascade,
    subject_id uuid not null
        constraint subject_fk
            references subject
            on update cascade on delete cascade
);

alter table teachers_subjects
    owner to postgres;

create table groups
(
    id             uuid      default gen_random_uuid() not null
        constraint groups_pk
            primary key,
    title          varchar                             not null,
    year           integer   default 1                 not null,
    specialization varchar                             not null,
    created_at     timestamp default CURRENT_TIMESTAMP not null,
    updated_at     timestamp default CURRENT_TIMESTAMP not null
);

alter table groups
    owner to postgres;

create table student
(
    id         uuid default gen_random_uuid() not null
        constraint students_pkey
            primary key,
    first_name text                           not null,
    last_name  text                           not null,
    created_at time default CURRENT_TIMESTAMP not null,
    updated_at time default CURRENT_TIMESTAMP not null,
    group_id   uuid
        constraint student_group_id_fk
            references groups
);

alter table student
    owner to postgres;

create table identity
(
    id                uuid    default gen_random_uuid() not null
        constraint identity_pk
            primary key,
    username          varchar                           not null,
    password          varchar                           not null,
    type              varchar(64)                       not null,
    email             varchar                           not null,
    is_email_verified boolean default false,
    role              varchar,
    status            varchar,
    student_id        uuid
        constraint student_fk
            references student
            on update cascade on delete cascade,
    teacher_id        uuid
        constraint teacher_fk
            references teacher
            on update cascade on delete cascade,
    admin_id          uuid
        constraint admin_fk
            references admin
            on update cascade on delete cascade
);

alter table identity
    owner to postgres;

create table messages
(
    id          uuid default gen_random_uuid() not null
        constraint messages_pk
            primary key,
    sender_id   uuid                           not null
        constraint sender_fk
            references identity
            on update cascade on delete cascade,
    receiver_id uuid
        constraint receiver_fk
            references identity
            on update cascade on delete cascade,
    title       varchar                        not null,
    text        varchar                        not null
);

alter table messages
    owner to postgres;

create table marks
(
    id         uuid default gen_random_uuid() not null
        constraint marks_pk
            primary key,
    teacher_id uuid                           not null
        constraint teacher_fk
            references teacher
            on update cascade on delete cascade,
    student_id uuid                           not null
        constraint student_fk
            references student
            on update cascade on delete cascade,
    subject_id uuid                           not null
        constraint subject_fk
            references subject
            on update cascade on delete cascade,
    mark       integer                        not null,
    date       timestamp                      not null
);

alter table marks
    owner to postgres;

create table schedule
(
    id         uuid default gen_random_uuid() not null
        primary key,
    teacher_id uuid                           not null
        constraint teacher_id
            references teacher
            on update cascade on delete cascade,
    group_id   uuid                           not null
        constraint group_id
            references groups
            on update cascade on delete cascade,
    subject_id uuid                           not null
        constraint subject_id
            references subject
            on update cascade on delete cascade,
    pair       integer                        not null
);

alter table schedule
    owner to postgres;

INSERT INTO public.groups (id, title, year, specialization, created_at, updated_at) VALUES ('c4a5ce19-4063-4601-99e1-05b09b03eed2', 'test', 1, 'ISIP', '2023-10-05 10:06:56.739142', '2023-10-05 10:06:56.739166');


INSERT INTO public.admin (id, first_name, last_name, created_at, updated_at) VALUES ('0516fef8-367d-44d5-9007-60d47830f737', 'admin', 'admin', '2023-10-04 22:23:45.131334', '2023-10-04 22:23:45.131366');
INSERT INTO public.student (id, first_name, last_name, created_at, updated_at, group_id) VALUES ('fc37fa3e-c6ef-4aa5-8f1e-5df52e9b17f5', 'student', 'student', '22:27:08', '22:27:08', 'c4a5ce19-4063-4601-99e1-05b09b03eed2');
INSERT INTO public.teacher (id, first_name, last_name, created_at, updated_at) VALUES ('b48b31a3-3fbc-49ec-b342-92f1460c6060', 'teacher', 'teacher', '2023-10-05 10:02:32.652375', '2023-10-05 10:02:32.652406');

INSERT INTO public.identity (id, username, password, type, email, is_email_verified, role, status, student_id, teacher_id, admin_id) VALUES ('a80caa05-f968-4f7d-83cc-9ec8117f5cd9', 'admin', '$2a$10$4tEnpaePMkQkBsbKGECUlOQzwM/DjTufNgdn/z0jKcDGtw9stKEhG', 'ADMIN', 'admin@email.com', false, 'ROLE_ADMIN', 'ACTIVE', null, null, '0516fef8-367d-44d5-9007-60d47830f737');
INSERT INTO public.identity (id, username, password, type, email, is_email_verified, role, status, student_id, teacher_id, admin_id) VALUES ('8cb3805a-6d6b-4ce5-a793-55f68c8204e8', 'student', '$2a$10$H2z5CJ4XgssNxAF4E2dmV.KX8fe2QCRK/RLJ4L9lYfuD37j22mkiu', 'STUDENT', 'student@email.com', false, 'ROLE_STUDENT', 'ACTIVE', 'fc37fa3e-c6ef-4aa5-8f1e-5df52e9b17f5', null, null);
INSERT INTO public.identity (id, username, password, type, email, is_email_verified, role, status, student_id, teacher_id, admin_id) VALUES ('4fab0526-ff2c-4951-bf58-50758d174df4', 'teacher', '$2a$10$BHI.YVrbjlu5nQ9rX.7nXe3loGh0q/8s1kMVcJNygXvlWzqTHb7d6', 'TEACHER', 'teacher@email.com', false, 'ROLE_TEACHER', 'ACTIVE', null, 'b48b31a3-3fbc-49ec-b342-92f1460c6060', null);
