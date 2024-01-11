create table teacher
(
    id         uuid    not null
        constraint teacher_pk
            primary key,
    first_name varchar not null,
    last_name  varchar not null,
    patronymic varchar
);

alter table teacher
    owner to postgres;

create table admin
(
    id         uuid    not null
        constraint admin_pk
            primary key,
    first_name varchar not null,
    last_name  varchar not null,
    patronymic varchar
);

alter table admin
    owner to postgres;

create table role
(
    id   uuid    not null
        primary key,
    name varchar not null
);

alter table role
    owner to postgres;

create table token
(
    id       uuid    not null
        constraint token_pk
            primary key,
    username varchar not null,
    value    varchar not null
);

alter table token
    owner to postgres;

create table groups
(
    id    uuid    not null
        constraint group_pkey
            primary key,
    title varchar not null,
    year  integer not null
);

alter table groups
    owner to postgres;

create table student
(
    id         uuid    not null
        constraint student_pk
            primary key,
    first_name varchar not null,
    last_name  varchar not null,
    patronymic varchar,
    group_id   uuid    not null
        constraint group_fk
            references groups
);

alter table student
    owner to postgres;

create table identity
(
    id         uuid    not null
        constraint identity_pk
            primary key,
    type       varchar not null,
    student_id uuid
        constraint student_fk
            references student
            on update cascade on delete cascade,
    teacher_id uuid
        constraint teacher_fk
            references teacher
            on update cascade on delete cascade,
    admin_id   uuid
        constraint admin_fk
            references admin
            on update cascade on delete cascade
);

alter table identity
    owner to postgres;

create table credentials
(
    id                uuid                  not null
        constraint credentials_pk
            primary key,
    identity_id       uuid                  not null
        constraint identity_fk
            references identity
            on update cascade on delete cascade,
    username          varchar               not null,
    password          varchar               not null,
    email             varchar               not null,
    is_email_verified boolean default false not null,
    created_at        timestamp             not null,
    updated_at        timestamp             not null
);

alter table credentials
    owner to postgres;

create table credentials_roles
(
    id             uuid default gen_random_uuid() not null
        constraint identity_roles_pk
            primary key,
    credentials_id uuid                           not null
        constraint credentials_fk
            references credentials
            on update cascade on delete cascade,
    role_id        uuid                           not null
        constraint role_fk
            references role
            on update cascade on delete cascade
);

alter table credentials_roles
    owner to postgres;

create table subjects
(
    id    uuid    not null
        constraint subjects_pk
            primary key,
    title varchar not null
);

alter table subjects
    owner to postgres;

create table teachers_groups
(
    id         uuid default gen_random_uuid() not null
        primary key,
    teacher_id uuid                           not null
        constraint teacher_fk
            references teacher
            on update cascade on delete cascade,
    group_id   uuid                           not null
        constraint group_fk
            references groups
            on update cascade on delete cascade
);

alter table teachers_groups
    owner to postgres;

create table teachers_subjects
(
    id         uuid default gen_random_uuid() not null
        primary key,
    teacher_id uuid                           not null
        constraint teacher_fk
            references teacher
            on update cascade on delete cascade,
    subject_id uuid                           not null
        constraint subject_fk
            references subjects
            on update cascade on delete cascade
);

alter table teachers_subjects
    owner to postgres;

create table messages
(
    id          uuid    not null
        primary key,
    sender_id   uuid    not null
        constraint sender_fk
            references identity,
    receiver_id uuid    not null
        constraint receiver_fk
            references identity,
    title       varchar not null,
    message     varchar not null
);

alter table messages
    owner to postgres;

create table groups_subjects
(
    id         uuid default gen_random_uuid() not null
        primary key,
    group_id   uuid                           not null
        constraint group_fk
            references groups,
    subject_id uuid                           not null
        constraint subject_id
            references subjects
);

alter table groups_subjects
    owner to postgres;

create table verification_codes
(
    id         uuid      not null
        constraint verification_codes_pk
            primary key,
    value      varchar   not null,
    expires_at timestamp not null,
    email      varchar   not null
);

alter table verification_codes
    owner to postgres;

create table schedule
(
    id         uuid    not null
        constraint schedule_pk
            primary key,
    teacher_id uuid    not null
        constraint teacher_fk
            references teacher,
    group_id   uuid    not null
        constraint group_fk
            references groups,
    subject_id uuid    not null
        constraint subject_fk
            references subjects,
    pair       integer not null,
    status     text    not null,
    date       date    not null
);

alter table schedule
    owner to postgres;

create table marks
(
    id          uuid    not null
        primary key,
    teacher_id  uuid    not null
        constraint teacher_fk
            references teacher,
    student_id  uuid    not null
        constraint student_fk
            references student,
    subject_id  uuid    not null
        constraint subject_id
            references subjects,
    mark        varchar not null,
    schedule_id uuid    not null
        constraint schedule_fk
            references schedule
);

alter table marks
    owner to postgres;


-- add roles
INSERT INTO public.role (id, name) VALUES ('756ad1c2-5948-452b-b243-ea97cb73435b', 'ROLE_STUDENT');
INSERT INTO public.role (id, name) VALUES ('22252d63-8432-4bec-9627-1a956d02da32', 'ROLE_TEACHER');
INSERT INTO public.role (id, name) VALUES ('b27fa679-567a-453e-9148-a532a4684d4c', 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES ('d1cc4164-e948-4170-ac7e-d5fbe912363d', 'ROLE_METHODIST');

-- add subjects
INSERT INTO public.subjects (id, title) VALUES ('d4af7487-e906-4732-9e7e-4ead31d8d697', 'maths');
INSERT INTO public.subjects (id, title) VALUES ('6f916225-6e58-43eb-ace0-e7a2eb0205c3', 'language');

-- add group
INSERT INTO public.groups (id, title, year) VALUES ('10c09a5c-a937-4661-bc07-a8b4414c6e1a', '3ИСИП-621', 1);

-- add subjects to the group
INSERT INTO public.groups_subjects (id, group_id, subject_id) VALUES ('13cb8b45-8145-4f24-9d36-098593ca7b46', '10c09a5c-a937-4661-bc07-a8b4414c6e1a', 'd4af7487-e906-4732-9e7e-4ead31d8d697');
INSERT INTO public.groups_subjects (id, group_id, subject_id) VALUES ('5ad8af50-08c4-41b3-b953-fc83f3a59a7b', '10c09a5c-a937-4661-bc07-a8b4414c6e1a', '6f916225-6e58-43eb-ace0-e7a2eb0205c3');

-- add admin
INSERT INTO public.admin (id, first_name, last_name, patronymic) VALUES ('1f0f85f6-1fc9-448d-8c71-4d6e836d6d4f', 'admin', 'admin', 'admin');
INSERT INTO public.identity (id, type, student_id, teacher_id, admin_id) VALUES ('bfc1a222-2825-4765-a597-c20bc37a5256', 'ADMIN', null, null, '1f0f85f6-1fc9-448d-8c71-4d6e836d6d4f');
INSERT INTO public.credentials (id, identity_id, username, password, email, is_email_verified, created_at, updated_at) VALUES ('abf4e988-74e9-4f05-af11-f315056b25c8', 'bfc1a222-2825-4765-a597-c20bc37a5256', 'admin', '$2a$12$VCaAwdGOSAPi3pZbQ5hGk.jRnIt8O4Xx0UlK2IGei12awxK.jYZUi', 'admin@g,ail.com', false, '2023-10-11 21:46:04.000000', '2023-10-11 21:46:08.000000');
INSERT INTO public.credentials_roles (id, credentials_id, role_id) VALUES ('203272ea-2b28-4aa5-ad3d-375d16e709c7', 'abf4e988-74e9-4f05-af11-f315056b25c8', 'b27fa679-567a-453e-9148-a532a4684d4c');

-- add teacher
INSERT INTO public.teacher (id, first_name, last_name, patronymic) VALUES ('0a0d6034-9b49-434a-a005-60cf8b2bf00b', 'teacher', 'teacher', 'teacher');
INSERT INTO public.identity (id, type, student_id, teacher_id, admin_id) VALUES ('c9024146-6ffe-4486-9b10-9ab976c5f00a', 'TEACHER', null, '0a0d6034-9b49-434a-a005-60cf8b2bf00b', null);
INSERT INTO public.credentials (id, identity_id, username, password, email, is_email_verified, created_at, updated_at) VALUES ('1fd90998-a4bd-4cca-b6e1-443ffbfc1ee3', 'c9024146-6ffe-4486-9b10-9ab976c5f00a', 'teacher', '$2a$10$azT7s/unxwFLiG.cecigPuZAS1TNJcDTPCTcF6UmWimyT/L8F7U/.', 'teacher@gmail.com', false, '2024-01-11 15:02:36.364480', '2024-01-11 15:02:36.365114');
INSERT INTO public.credentials_roles (id, credentials_id, role_id) VALUES ('6efd9660-788b-43f3-8043-30e3e83ab2e5', '1fd90998-a4bd-4cca-b6e1-443ffbfc1ee3', '22252d63-8432-4bec-9627-1a956d02da32');

-- add student
INSERT INTO public.student (id, first_name, last_name, patronymic, group_id) VALUES ('dc79e78f-bdcd-46b2-afea-5279776c7236', 'student', 'student', 'student', '10c09a5c-a937-4661-bc07-a8b4414c6e1a');
INSERT INTO public.identity (id, type, student_id, teacher_id, admin_id) VALUES ('c9ea7ed1-2e75-4346-a6e5-67cf11f06db1', 'STUDENT', 'dc79e78f-bdcd-46b2-afea-5279776c7236', null, null);
INSERT INTO public.credentials (id, identity_id, username, password, email, is_email_verified, created_at, updated_at) VALUES ('700bd902-9d20-455e-a270-9fc46d690a3a', 'c9ea7ed1-2e75-4346-a6e5-67cf11f06db1', 'student', '$2a$10$d6L3kNuFEr.gG8WREvuXhuA7zd57FcY/MV6luq6kxLg3pvyo6SvVG', 'student@gmail.com', false, '2024-01-11 15:07:01.790788', '2024-01-11 15:07:01.791233');
INSERT INTO public.credentials_roles (id, credentials_id, role_id) VALUES ('71639baa-2215-4f49-9a4c-39da4ec96a5b', '700bd902-9d20-455e-a270-9fc46d690a3a', '756ad1c2-5948-452b-b243-ea97cb73435b');

-- add schedule
INSERT INTO public.schedule (id, teacher_id, group_id, subject_id, pair, status, date) VALUES ('133e82d9-b9d3-4753-8b31-82b08bc24d61', '0a0d6034-9b49-434a-a005-60cf8b2bf00b', '10c09a5c-a937-4661-bc07-a8b4414c6e1a', 'd4af7487-e906-4732-9e7e-4ead31d8d697', 1, 'PLANNED', '2024-01-12');
INSERT INTO public.schedule (id, teacher_id, group_id, subject_id, pair, status, date) VALUES ('1a324f65-2f3f-472f-a352-542ee24530ed', '0a0d6034-9b49-434a-a005-60cf8b2bf00b', '10c09a5c-a937-4661-bc07-a8b4414c6e1a', 'd4af7487-e906-4732-9e7e-4ead31d8d697', 2, 'PLANNED', '2024-01-12');
INSERT INTO public.schedule (id, teacher_id, group_id, subject_id, pair, status, date) VALUES ('fa7872db-20fd-4446-9856-5cd72a91a53e', '0a0d6034-9b49-434a-a005-60cf8b2bf00b', '10c09a5c-a937-4661-bc07-a8b4414c6e1a', '6f916225-6e58-43eb-ace0-e7a2eb0205c3', 3, 'PLANNED', '2024-01-12');
INSERT INTO public.schedule (id, teacher_id, group_id, subject_id, pair, status, date) VALUES ('e665948b-6ba6-4a2b-b99b-da505a0735fe', '0a0d6034-9b49-434a-a005-60cf8b2bf00b', '10c09a5c-a937-4661-bc07-a8b4414c6e1a', '6f916225-6e58-43eb-ace0-e7a2eb0205c3', 4, 'PLANNED', '2024-01-12');
