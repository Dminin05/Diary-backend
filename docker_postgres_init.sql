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
    id             uuid not null
        constraint identity_roles_pk
            primary key,
    credentials_id uuid not null
        constraint credentials_fk
            references credentials
            on update cascade on delete cascade,
    role_id        uuid not null
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
    id         uuid not null
        primary key,
    teacher_id uuid not null
        constraint teacher_fk
            references teacher
            on update cascade on delete cascade,
    group_id   uuid not null
        constraint group_fk
            references groups
            on update cascade on delete cascade
);

alter table teachers_groups
    owner to postgres;

create table teachers_subjects
(
    id         uuid not null
        primary key,
    teacher_id uuid not null
        constraint teacher_fk
            references teacher
            on update cascade on delete cascade,
    subject_id uuid not null
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

create table marks
(
    id         uuid    not null
        primary key,
    teacher_id uuid    not null
        constraint teacher_fk
            references teacher,
    student_id uuid    not null
        constraint student_fk
            references student,
    subject_id uuid    not null
        constraint subject_id
            references subjects,
    mark       varchar not null
);

alter table marks
    owner to postgres;

create table groups_subjects
(
    id         uuid not null
        primary key,
    group_id   uuid not null
        constraint group_fk
            references groups,
    subject_id uuid not null
        constraint subject_id
            references subjects
);

alter table groups_subjects
    owner to postgres;







INSERT INTO public.role (id, name) VALUES ('756ad1c2-5948-452b-b243-ea97cb73435b', 'ROLE_STUDENT');
INSERT INTO public.role (id, name) VALUES ('22252d63-8432-4bec-9627-1a956d02da32', 'ROLE_TEACHER');
INSERT INTO public.role (id, name) VALUES ('b27fa679-567a-453e-9148-a532a4684d4c', 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES ('d1cc4164-e948-4170-ac7e-d5fbe912363d', 'ROLE_METHODIST');

INSERT INTO public.admin (id, first_name, last_name, patronymic) VALUES ('1f0f85f6-1fc9-448d-8c71-4d6e836d6d4f', 'admin', 'admin', 'admin');
INSERT INTO public.identity (id, type, student_id, teacher_id, admin_id) VALUES ('bfc1a222-2825-4765-a597-c20bc37a5256', 'ADMIN', null, null, '1f0f85f6-1fc9-448d-8c71-4d6e836d6d4f');
INSERT INTO public.credentials (id, identity_id, username, password, email, is_email_verified, created_at, updated_at) VALUES ('abf4e988-74e9-4f05-af11-f315056b25c8', 'bfc1a222-2825-4765-a597-c20bc37a5256', 'admin', '$2a$12$VCaAwdGOSAPi3pZbQ5hGk.jRnIt8O4Xx0UlK2IGei12awxK.jYZUi', 'admin@g,ail.com', false, '2023-10-11 21:46:04.000000', '2023-10-11 21:46:08.000000');
INSERT INTO public.credentials_roles (id, credentials_id, role_id) VALUES ('203272ea-2b28-4aa5-ad3d-375d16e709c7', 'abf4e988-74e9-4f05-af11-f315056b25c8', 'b27fa679-567a-453e-9148-a532a4684d4c');

