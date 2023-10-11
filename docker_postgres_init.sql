create table student
(
    id         uuid default gen_random_uuid() not null
        constraint student_pk
            primary key,
    first_name varchar                        not null,
    last_name  varchar                        not null,
    patronymic varchar
);

alter table student
    owner to postgres;

create table teacher
(
    id         uuid default gen_random_uuid() not null
        constraint teacher_pk
            primary key,
    first_name varchar                        not null,
    last_name  varchar                        not null,
    patronymic varchar
);

alter table teacher
    owner to postgres;

create table admin
(
    id         uuid default gen_random_uuid() not null
        constraint admin_pk
            primary key,
    first_name varchar                        not null,
    last_name  varchar                        not null,
    patronymic varchar
);

alter table admin
    owner to postgres;

create table identity
(
    id         uuid default gen_random_uuid() not null
        constraint identity_pk
            primary key,
    type       varchar                        not null,
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
    id                uuid    default gen_random_uuid() not null
        constraint credentials_pk
            primary key,
    identity_id       uuid                              not null
        constraint identity_fk
            references identity
            on update cascade on delete cascade,
    username          varchar                           not null,
    password          varchar                           not null,
    email             varchar                           not null,
    is_email_verified boolean default false             not null,
    created_at        timestamp                         not null,
    updated_at        timestamp                         not null
);

alter table credentials
    owner to postgres;

create table role
(
    id   uuid default gen_random_uuid() not null
        primary key,
    name varchar                        not null
);

alter table role
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

create table token
(
    id       uuid default gen_random_uuid() not null
        constraint token_pk
            primary key,
    username varchar                        not null,
    value    varchar                        not null
);

alter table token
    owner to postgres;

